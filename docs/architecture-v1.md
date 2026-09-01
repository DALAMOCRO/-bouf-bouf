# Architecture V1 — Bouf-Bouf

## Recommandation

La proposition **Android natif (Kotlin + Jetpack Compose) / FastAPI / PostgreSQL / Docker** est adaptée à une startup mobile commerciale. Elle offre une excellente expérience Android, une API productive et typée, et une base relationnelle solide pour les interactions sociales.

Je recommande de conserver cette stack avec deux compléments essentiels dès le socle :

1. un stockage objet compatible S3 pour les fichiers vidéo et leurs miniatures ;
2. une file de travaux asynchrones pour le transcodage, les miniatures et la modération.

Ne pas transformer la V1 en microservices : un **monolithe modulaire** FastAPI est plus rapide à faire évoluer, tester et déployer. Extraire des services ne deviendra pertinent qu'avec une charge vidéo ou des équipes distinctes.

## Périmètre V1

| Inclus | Différé |
| --- | --- |
| feed vertical, vidéo, profils, publication, follows, likes, commentaires, sauvegardes, hashtags, recherche simple | paiement, livraison, publicité, marketplace, rémunération créateurs, recommandations IA complexes |
| mise en avant éditoriale « Star du Mois » | concours et sponsoring automatisés |

## Vue d'ensemble

```text
Android Compose
    │ HTTPS / REST + CDN
    ▼
FastAPI (monolithe modulaire) ── PostgreSQL
    │           │
    │           ├── Redis + worker (transcodage, miniatures, notifications)
    ▼           │
Stockage objet S3 ── CDN / streaming adaptatif
```

Le mobile demande au backend une URL de lecture CDN et envoie directement le fichier au stockage objet via une URL signée. L'API ne doit pas servir les gros fichiers vidéo.

## Modules backend

```text
backend/app/
  api/v1/          endpoints HTTP et schémas de requête
  core/            configuration, sécurité, journalisation
  db/              session, modèles, migrations Alembic
  domains/
    identity/      comptes, sessions, rôles
    content/       vidéos, recettes, hashtags, publication
    social/        follows, likes, commentaires, sauvegardes
    discovery/     feed, recherche, signaux de classement
    editorial/     Star du Mois et contenu mis en avant
    media/         upload signé et états de traitement
  workers/         tâches asynchrones
```

L'application Android suivra une structure feature-first : `core/` (réseau, design system, auth), puis `feature/feed`, `feature/publish`, `feature/profile`, `feature/search` et `feature/editorial`. Chaque feature sépare UI Compose, ViewModel et sources de données.

## Modèle de données principal

| Entité | Champs importants | Relations |
| --- | --- | --- |
| `users` | id, handle, display_name, bio, avatar_url, role, status | publie et interagit |
| `videos` | id, author_id, caption, visibility, media_status, published_at | possède médias et métriques |
| `video_assets` | id, video_id, kind, object_key, duration_ms, width, height | original, manifest, miniature |
| `recipes` | id, video_id, title, prep_minutes, servings, instructions | ingrédients associés |
| `ingredients` / `recipe_ingredients` | canonical_name, quantity, unit | recherche et Star du Mois |
| `hashtags` / `video_hashtags` | normalized_name | indexation |
| `follows` | follower_id, followee_id, created_at | relation utilisateur-utilisateur |
| `likes`, `saves`, `comments` | user_id, video_id, created_at | interactions idempotentes |
| `monthly_stars` | ingredient_id, starts_on, ends_on, status | événement éditorial |
| `feed_impressions` | user_id, video_id, surface, watched_ms | signaux de classement et analytics |

Toutes les tables exposées utilisent des UUID/ULID publics, des index composés sur leurs relations fréquentes et des contraintes d'unicité (`follower_id, followee_id`, par exemple).

## Feed vidéo : composants nécessaires

- Endpoint paginé par curseur, avec contrat stable et cache court.
- Lecteur unique à écran complet : préchargement contrôlé de l'élément suivant, autoplay seulement lorsque l'écran est visible.
- Streaming HLS/DASH adaptatif via CDN, miniature et fallback réseau lent.
- Événements de vue (impression, début, durée regardée, complétion, actions) envoyés par lots.
- Première version de classement explicable : récence, langue/région, affinité follows, complétion et interactions ; avec diversité par créateur/ingrédient.
- États de modération : `draft`, `processing`, `ready`, `rejected`, `removed`.

## Sécurité et exploitation

- Mots de passe Argon2id ; JWT d'accès court et refresh tokens hachés/rotatifs.
- HTTPS obligatoire en production, CORS restreint, limites de débit sur auth et publication.
- Validation Pydantic, contrôle d'autorisation au niveau objet, logs structurés sans données sensibles.
- URLs d'upload signées, contrôle MIME/taille, transcodage isolé et scan de contenu à introduire avant l'ouverture publique.
- Sauvegardes PostgreSQL, surveillance d'erreurs, métriques, politique de rétention et procédure de suppression de compte avant lancement.

## Risques techniques majeurs

1. **Coût et performance vidéo** — HLS, CDN, limites de durée/taille et quotas par créateur sont indispensables.
2. **Modération et droits** — prévoir signalement, retrait, preuve de droits et processus humain avant l'ouverture des publications.
3. **Recommandation froide** — démarrer avec sélection éditoriale + règles plutôt qu'une IA non entraînée.
4. **Réseau mobile marocain** — bitrate adaptatif, consommation de données et reprise d'upload doivent être testés sur appareils réels.
5. **Vie privée** — consentement analytics, suppression/export de données et règles de localisation devront être définis avec conseil juridique.

## Décisions à prendre avant développement fonctionnel

- Authentification initiale : e-mail, téléphone, Google/Apple, ou une combinaison.
- Prestataire vidéo : Cloudflare Stream/Mux (lancement rapide) ou S3 + MediaConvert/FFmpeg (plus de contrôle, plus d'exploitation).
- Hébergement et région de données, budget CDN/streaming et durée maximale des vidéos.
- Politique de modération, âge minimum, droits musicaux et conditions créateurs.
- Langues de lancement (Darija, arabe, français) et règles de recherche/translittération.
- Statut exact des restaurants/traiteurs pour la V1 : profil créateur enrichi recommandé, plutôt qu'un module professionnel complet.

## Plan de développement

1. **Fondations** — authentification, profils, migrations, CI, observabilité, environnements.
2. **Média** — upload signé, pipeline vidéo, publication brouillon/ready, lecteur Android.
3. **Boucle sociale** — feed cursé, likes, sauvegardes, follows, commentaires, profil public.
4. **Découverte** — hashtags, recherche, feed de règles, événements analytics.
5. **Éditorial et bêta** — Star du Mois, sélection éditoriale, signalement, tests réels au Maroc et durcissement sécurité.

Chaque phase se termine par des tests automatisés et une validation produit avec un petit groupe de créateurs pilotes.
