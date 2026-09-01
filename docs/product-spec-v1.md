# Bouf-Bouf V1 — Cahier des charges produit officiel

**Statut :** référence produit à valider avant le développement du MVP  
**Périmètre :** lancement Maroc, architecture prête pour l’international  
**Principe :** ce document ne constitue pas une décision de développement ; toute décision marquée `[DECISION FONDATEUR]` requiert une validation explicite.

---

## 1. Vision

Bouf-Bouf est **le réseau social de la cuisine** : une application mobile vidéo où l’on ouvre le feed pour regarder, découvrir et s’inspirer, même sans savoir quoi chercher.

La promesse est : **« Je veux juste scroller, mais uniquement de la cuisine. »**

Dans la situation phare, une personne rentre chez elle, ne sait pas quoi cuisiner, ouvre Bouf-Bouf et trouve une idée en quelques vidéos. La recette structure ensuite cette inspiration lorsqu’elle veut réellement cuisiner ; elle n’est pas le point d’entrée obligatoire.

La V1 doit valider une boucle simple :

```text
Regarder → Découvrir → S’inspirer → Ouvrir la recette → Sauvegarder → Cuisiner
                                     ↘ Suivre / publier ↗
```

## 2. Positionnement

Bouf-Bouf n’est ni une base de recettes classique ni un clone généraliste de TikTok. C’est un réseau social dont l’univers de contenu est exclusivement culinaire : recettes, astuces, techniques, plats familiaux, cuisines du monde, street food, chefs et professionnels.

Le Maroc est le marché de lancement et une source d’identité éditoriale, notamment pour la cuisine familiale et les traditions. Il ne doit pas être une limite technique, linguistique ni conceptuelle : l’application doit pouvoir accueillir des créateurs et contenus internationaux.

## 3. Proposition de valeur

| Pour l’utilisateur | Pour le créateur | Pour Bouf-Bouf |
| --- | --- | --- |
| Inspiration culinaire immédiate, agréable à regarder et réutilisable | Audience entièrement intéressée par la cuisine et publication simple | Une habitude de scroll culinaire qui peut devenir une communauté et un média |

Les bénéfices fonctionnels sont : découvrir une idée rapidement, comprendre une recette à la demande, conserver les idées utiles, et suivre des personnes qui cuisinent comme on aime.

Les bénéfices émotionnels sont : ne plus être bloqué devant un repas à décider, retrouver des saveurs familières, découvrir de nouvelles cultures et avoir envie d’essayer.

## 4. Utilisateurs cibles

La priorité de la V1 est l’utilisateur qui consomme du contenu pour s’inspirer. Les créateurs sont le second public indispensable pour assurer une offre régulière et originale. Les restaurants et traiteurs viennent ensuite avec une présence éditoriale, pas transactionnelle.

### Personas comportementaux

| Persona | Comportement | Besoin | Moment de vérité |
| --- | --- | --- | --- |
| L’inspiré du soir | scrolle sans requête précise après le travail | une idée accessible immédiatement | sauvegarde ou ouvre une recette en moins de 2 minutes |
| L’amateur curieux | regarde techniques, cuisines du monde et astuces | apprendre sans cours long | termine une vidéo et suit un créateur |
| Le gardien des recettes familiales | cherche traditions, plats de famille et histoires culinaires | retrouver/transmettre une culture | découvre « Comme chez nous » et partage une recette |
| Le créateur émergent | veut publier et trouver une audience culinaire | diffusion simple et feedback clair | sa première vidéo est publiée sans friction |
| Le professionnel local | souhaite montrer son savoir-faire | visibilité et contact sans marketplace | un spectateur ouvre son profil ou le contacte |

## 5. Principes produit

1. **Le feed avant tout.** La vidéo est le point d’entrée et garde la priorité visuelle.
2. **Découvrir avant de s’inscrire.** Le visionnage reste ouvert ; le compte protège les actions personnelles.
3. **Une information au bon moment.** La recette est accessible, mais ne recouvre pas la vidéo.
4. **Cuisine seulement.** Les contenus non culinaires ne sont pas encouragés par les règles, la taxonomie ou le feed.
5. **Simplicité avant exhaustivité.** Peu de catégories, de filtres et d’états dans le MVP.
6. **Culture sans enfermement.** Le contenu marocain est éditorialement valorisé sans exclure le reste du monde.
7. **Transparence.** Source externe, partenariat et contenu sponsorisé devront être identifiables.

## 6. User journeys

### A. Nouvel utilisateur

1. Ouvre l’application ; splash court.
2. Voit un onboarding de trois écrans maximum, qu’il peut passer.
3. Arrive sur un feed jouable sans compte.
4. Indique facultativement quelques goûts/langues ou continue directement.
5. À une action de valeur (sauvegarder, suivre, commenter, publier), s’identifie.
6. Revient exactement à la vidéo et à l’action demandée.

**Succès :** première session avec une vidéo regardée significativement, une recette ouverte ou une sauvegarde.

### B. Utilisateur en quête d’inspiration

1. Ouvre le feed « Pour vous ».
2. Swipe plusieurs vidéos à lecture automatique.
3. Le feed apprend des visionnages, complétions, likes, sauvegardes et « pas intéressé ».
4. Il s’arrête sur une idée, garde la vidéo ou ouvre la recette.

**Succès :** sentiment d’avoir trouvé une idée ; un signal durable est émis.

### C. Utilisateur qui trouve une recette

1. Depuis une vidéo, touche « Voir la recette ».
2. Consulte temps, portions, ingrédients et étapes ; la vidéo reste disponible en tête.
3. Sauvegarde la recette/vidéo pour plus tard ou revient au feed.

**Succès :** recette ouverte puis sauvegardée ou reconsultée.

### D. Utilisateur qui sauvegarde

1. Touche l’icône de sauvegarde dans le feed ou la page recette.
2. Si visiteur, s’inscrit/se connecte et revient au contenu.
3. La vidéo apparaît dans sa collection privée « Sauvegardes ».
4. Il la retrouve le jour où il cuisine.

**Succès :** réouverture d’une sauvegarde à une session ultérieure.

### E. Utilisateur qui devient créateur

1. Depuis l’onglet central « Publier », découvre que chacun peut partager une vidéo culinaire.
2. Se connecte, importe ou capture une vidéo, ajoute légende et éventuellement recette.
3. Accepte les règles de publication et atteste des droits.
4. Publie ; voit l’état « traitement », puis « publiée » ou un motif de retrait.

**Succès :** première publication réussie et conforme.

### F. Créateur qui publie régulièrement

1. Publie ses vidéos, suit les commentaires et consulte son profil.
2. Utilise hashtags, ingrédients et Star du Mois lorsque pertinent.
3. Gagne des abonnés via le feed et ses vidéos.

**Succès :** cadence durable, interactions de qualité, absence de violations répétées.

### G. Restaurant / traiteur

1. Crée d’abord un compte créateur.
2. Demande un statut professionnel avec pièces/validation selon règle retenue.
3. Ajoute présentation, zone, horaires et moyen de contact ; publie des vidéos.
4. Un spectateur le suit ou le contacte en dehors de Bouf-Bouf.

**Succès :** visibilité/contacts qualifiés sans panier, commande ni paiement dans l’application.

### H. Découverte de la Star du Mois

1. Voit un module discret dans Découvrir ou occasionnellement dans le feed.
2. Ouvre la page de l’ingrédient vedette.
3. Parcourt des recettes, astuces et créateurs associés.
4. Vote ou participe au challenge après connexion, si la fonction est activée.

**Succès :** plus de vidéos consultées/sauvegardées et de créateurs participants, sans cannibaliser le feed principal.

## 7. Navigation V1

Navigation basse recommandée :

```text
Accueil     Découvrir     Publier     Activité     Profil
```

| Destination | Rôle |
| --- | --- |
| Accueil | feed « Pour vous » et « Abonnements » |
| Découvrir | recherche, tendances, hashtags, ingrédients, Star du Mois |
| Publier | point d’entrée de création, protégé par connexion |
| Activité | notifications et interactions liées au compte |
| Profil | identité, vidéos, réglages, accès aux sauvegardes privées |

**Décision de navigation :** les sauvegardes ne constituent pas un onglet bas séparé. Elles restent une destination privée depuis le profil, afin de conserver cinq onglets et une barre basse équilibrée.

`[DECISION FONDATEUR]` Valider ce choix ou remplacer « Activité » par « Sauvegardes ».

| Choix A | Choix B |
| --- | --- |
| Activité dans la barre basse ; sauvegardes dans Profil | Sauvegardes dans la barre basse ; activité via icône |
| Avantage : développe les relations et donne une place claire aux commentaires/follows | Avantage : soutient le geste « je cuisine plus tard » |
| Inconvénient : les sauvegardes sont moins visibles | Inconvénient : notifications moins découvrables |
| **Recommandation : A** pour un réseau social à long terme ; mesurer l’usage des sauvegardes durant la bêta. |

## 8. Écrans V1

### 8.1 Splash screen

- **Objectif :** confirmer la marque et restaurer session/configuration sans attente perceptible.
- **Éléments :** logo Bouf-Bouf, fond de marque chaleureux, loader seulement si nécessaire.
- **Navigation :** onboarding lors de la première visite, sinon feed.
- **Visiteur/connecté :** même expérience ; le connecté ne doit pas attendre une connexion avant de voir le feed.

### 8.2 Onboarding léger et préférences

- **Objectif :** expliquer « scroller de la cuisine », « sauvegarder », « publier ».
- **Éléments :** trois écrans maximum, bouton « Continuer », lien « Passer » permanent.
- **Préférences facultatives :** goûts (rapide, marocain, végétarien, économique…), langues de contenu, ville facultative.
- **Navigation :** va directement au feed ; aucune permission système demandée dans ce tunnel.
- **Visiteur :** préférences locales migrables plus tard. **Connecté :** préférences synchronisées et modifiables.

### 8.3 Inscription / connexion

- **Objectif :** débloquer actions privées/sociales, pas filtrer la découverte.
- **Éléments :** fournisseurs d’authentification retenus, lien vers conditions/confidentialité, retour/fermeture.
- **Navigation :** reprend l’action demandée après succès.
- **Visiteur :** peut quitter vers le feed. **Connecté :** ne voit pas cet écran en usage normal.

### 8.4 Feed principal

- **Objectif :** inspiration immédiate, plein écran, une seule vidéo active.
- **Éléments :** onglets « Pour vous / Abonnements », vidéo 9:16, avatar/handle, légende courte, hashtags, actions, CTA recette s’il existe.
- **Navigation :** swipe vertical ; profil par avatar/handle ; recette par CTA ; commentaires/page détaillée par action.
- **Visiteur :** peut regarder, swiper, rechercher et partager ; connexion sur actions sociales/persistance. **Connecté :** like, sauvegarde, follow et commentaire instantanés.
- **Données :** vidéo/manifest CDN, auteur, légende, hashtags, ingrédients repères, recette associée, compteurs, états d’interaction et raison de classement.

### 8.5 Page vidéo / recette

- **Objectif :** passer de « cela a l’air bon » à « je peux le faire ».
- **Éléments :** lecteur, auteur, titre, temps, difficulté facultative, portions, ingrédients, étapes, hashtags, commentaires, sauvegarde/partage/signalement.
- **Navigation :** retour au feed ; auteur vers profil ; hashtags/ingrédients vers découverte.
- **Visiteur :** consulte librement, s’identifie pour interagir. **Connecté :** commente/sauvegarde/suit.
- **Données :** recette structurée, unités, quantités, durée, visibilité/modération, commentaires paginés.

### 8.6 Profils

**Profil utilisateur personnel :** avatar, handle, bio, compteurs, ses vidéos, modifier le profil, réglages et sauvegardes privées. Les vidéos aimées sont privées par défaut et peuvent être différées.

**Profil créateur :** identité publique, bio, localisation déclarative facultative, grille vidéos, bouton suivre, partage, compteurs. Le badge de vérification n’apparaît qu’avec une politique formelle.

- **Visiteur :** consulte un profil public ; suivre demande connexion. **Connecté :** gère son profil ou suit le créateur.
- **Données :** public/private profile fields, compteurs, vidéos publiées, relation follow.

### 8.7 Publication

- **Objectif :** publier une vidéo culinaire sans studio de montage complexe.
- **Étapes :** sélectionner/capturer → découper/choisir couverture → légende, hashtags et recette facultative → visibilité/règles/droits → upload/traitement → statut publié.
- **Éléments :** progression, brouillon, limite de durée/taille, avertissement droits, motif de rejet si nécessaire.
- **Visiteur :** authentification obligatoire avant création. **Connecté :** brouillon local, reprise d’upload, suivi d’état.
- **Données :** média source, métadonnées techniques, légende, tags, recette, déclaration de droits, état de modération.

### 8.8 Découvrir et recherche

- **Objectif :** servir une intention explicite après ou avant le scroll.
- **Éléments :** champ recherche, tendances, ingrédients/hashtags populaires, accès Star du Mois ; résultats Vidéos, Créateurs, Hashtags.
- **Navigation :** résultat vidéo vers page vidéo ; hashtag/ingrédient vers collection ; créateur vers profil.
- **Visiteur/connecté :** même consultation ; la recherche est un signal de personnalisation avec règles de confidentialité.
- **Données :** index contenu, normalisation hashtags et ingrédients, langue/variantes, popularité récente.

### 8.9 Hashtags et ingrédients

- **Objectif :** parcourir une famille de contenus sans taxonomie lourde.
- **Éléments :** titre normalisé, volume approximatif, vidéos à la une/récentes, filtres très limités.
- **Navigation :** chaque résultat ouvre une vidéo ou recette.
- **Données :** hashtag/ingrédient canonique, alias linguistiques, vidéos reliées et sélection éditoriale.

### 8.10 Sauvegardes

- **Objectif :** retrouver une idée au moment de cuisiner.
- **Éléments :** grille privée, tri par sauvegarde récente, option simple « Toutes / À cuisiner » si validée.
- **Navigation :** depuis Profil ; vidéo/récette consultable directement.
- **Visiteur :** invitation à créer un compte. **Connecté :** liste synchronisée, retrait immédiat.
- **Données :** relation utilisateur-vidéo, date de sauvegarde, état de disponibilité de la vidéo.

### 8.11 Activité / notifications

- **Objectif :** ramener vers les relations et les contenus personnels utiles.
- **Éléments :** nouveaux followers, likes/commentaires, réponses, état de publication, rappel Star du Mois opt-in.
- **Navigation :** chaque notification ouvre son objet cible. Paramètres de notifications accessibles depuis cet écran.
- **Visiteur :** aucune activité. **Connecté :** centre in-app ; push par consentement.
- **Données :** type, acteur, cible, lu/non lu, préférence utilisateur.

## 9. Feed détaillé

### Hiérarchie visuelle

```text
┌────────────────────────────────┐
│ Pour vous      Abonnements   🔔 │
│                                │
│           VIDÉO 9:16           │
│          lecture auto           │
│                                │
│                                │
│ @creator              ♡        │
│ Légende, 2 lignes max    💬     │
│ #hashtag #hashtag       🔖      │
│ Poulet · Citron · Olives ↗      │
│ [Voir la recette]              │
└────────────────────────────────┘
```

Ordre de priorité : **vidéo > créateur > contexte > actions**. La vidéo recouvre l’écran ; un dégradé bas garantit la lecture. Aucune liste longue d’ingrédients, menu ou promotion ne doit encombrer ce niveau.

### Comportement

- Démarre automatiquement lorsque la vidéo est majoritairement visible ; s’arrête en arrière-plan.
- Swipe vertical vers contenu suivant/précédent ; préchargement limité de l’élément suivant.
- Tap sur la vidéo pour pause/reprise ; appui long pour « Pas intéressé », signalement et copie de lien.
- Avatar/handle ouvrent le profil ; like, commentaire, sauvegarde et partage ont des cibles distinctes et persistantes.
- « Voir la recette » n’apparaît que si une recette structurée existe.
- « Abonnements » est chronologique ; « Pour vous » est classé avec règles simples.

### Personnalisation V1 explicable

| Signal | Effet de classement | Intensité / garde-fou |
| --- | --- | --- |
| visionnage long, complétion, relecture | hausse du sujet, format et créateur similaires | fort, mais plafonné |
| like | hausse d’affinité légère | ne crée pas seul une bulle |
| sauvegarde / recette ouverte | hausse d’affinité pour ingrédients, recettes et créateur | fort, signal d’utilité |
| follow | insertion de vidéos du créateur | onglet séparé pour l’ordre chronologique |
| recherche/hashtag/ingrédient | favorise contenu lié | durée limitée dans le temps |
| langue/ville choisies | favorise compréhension et éditorial local | ne bloque jamais l’international |
| swipe immédiat / pas intéressé | diminue thème, format ou créateur | annulation possible |

Le premier feed est éditorial et diversifié : cuisine marocaine, rapide, économique, familiale, techniques et cuisines internationales. Il doit éviter deux vidéos consécutives du même créateur et réserver une part de découverte. Il ne faut pas promettre une IA ; le comportement doit pouvoir être expliqué par des règles et des événements mesurés.

## 10. Page recette

La recette est liée à une vidéo, mais la vidéo peut exister sans recette structurée (astuce, street food, découverte). Sa page contient :

- vidéo avec possibilité de pause/relecture ;
- auteur et actions sociales ;
- titre, temps de préparation/cuisson si fourni, difficulté et portions facultatives ;
- ingrédients en liste repliable ;
- étapes repliables ;
- hashtags, commentaires, partage, sauvegarde et signalement.

`[DECISION FONDATEUR]` Niveau d’exigence de la recette pour une publication « recette ».

| Choix A | Choix B |
| --- | --- |
| Recette structurée obligatoire pour le type « recette » | Recette toujours facultative, même pour une recette |
| Avantage : meilleure utilité/recherche, qualité plus homogène | Avantage : publication plus rapide et plus de contenu initial |
| Inconvénient : friction créateur | Inconvénient : contenu inspirant mais peu actionnable |
| **Recommandation : A**, avec un type « astuce/découverte » où elle reste facultative. |

## 11. Création et publication

La publication V1 est volontairement sobre. Elle permet de capturer/importer une vidéo, couper le début/la fin, choisir une couverture, écrire une légende, associer hashtags et ingrédients, puis publier. Une recette structurée est demandée selon le type de contenu retenu.

À prévoir dans le parcours : brouillon, progression d’upload, traitement asynchrone, état de modération et message clair lors d’un refus. Les créateurs attestent qu’ils ont les droits nécessaires sur la vidéo, l’image, la musique et les éléments publiés.

Hors V1 : montage multicouche, effets, duos, lives, stories, bibliothèque musicale propriétaire, remix et monétisation.

## 12. Profils, créateurs et professionnels

### Créateurs

Un utilisateur devient créateur en publiant, sans seuil de followers. Le parcours est : utilisateur → créateur → créateur actif/populaire → professionnel vérifié. Les niveaux « populaire » ne doivent pas donner des droits automatiques en V1 ; ils servent à l’éditorial/pilotage interne jusqu’à ce qu’un programme transparent existe.

### Orientation éditoriale « Comme chez nous »

Une étiquette ou collection éditoriale peut mettre en valeur plats familiaux, traditions et transmission. Elle doit être éditoriale et non une catégorie vaste et confuse. Les propositions : recettes de grand-mère, plat de famille, tradition, histoire du plat. Aucun claim historique ne doit être présenté comme vérifié sans source/revue.

### Professionnels (évolution)

Un profil professionnel est un profil créateur vérifié et enrichi : nom commercial, présentation, vidéos, zone/localisation déclarative, horaires, téléphone/WhatsApp et lien de menu. Promotions, statistiques, menus complets, réservation, commande, livraison et paiement sont exclus du MVP.

`[DECISION FONDATEUR]` Moment de lancement du profil professionnel.

| Choix A | Choix B |
| --- | --- |
| Inclure une version légère dans la bêta V1 | Réserver les professionnels à une V1.1 après validation du feed |
| Avantage : premiers partenaires et contenu crédible | Avantage : concentration maximale sur la boucle consommateur/créateur |
| Inconvénient : vérification, support et attentes commerciales | Inconvénient : moins de levier partenaire initial |
| **Recommandation : B**, sauf si des partenaires pilotes apportent du contenu original garanti. |

## 13. Star du Mois

La Star du Mois est un événement éditorial signature qui complète le feed. Chaque mois, un fruit ou légume devient l’ingrédient vedette ; une page dédiée propose ses recettes, vidéos, astuces et créateurs.

### V1 basique

- ingrédient choisi par l’équipe éditoriale ;
- hero avec période et angle de saison ;
- collections « À la une », « Recettes », « Astuces », « Rapide », « Économique », « Maroc / monde » ;
- hashtag officiel et possibilité d’associer l’ingrédient à une publication ;
- sélection éditoriale de vidéos et créateurs.

Le vote, challenge, classement et concours ne sont activés que si les contrôles de compte, modération et règles opérationnelles sont prêts.

`[DECISION FONDATEUR]` Premier mécanisme communautaire de Star du Mois.

| Choix A | Choix B |
| --- | --- |
| Lancement éditorial seulement ; vote après bêta | Vote simple pour la Star suivante dès le lancement |
| Avantage : qualité, sécurité et rythme éditorial maîtrisés | Avantage : sentiment de participation immédiat |
| Inconvénient : moins d’interaction communautaire au départ | Inconvénient : fraude, choix peu saisonniers et charge support |
| **Recommandation : A** ; lancer un vote uniquement après comptes vérifiés et volume d’utilisateurs suffisant. |

### À terme

Votes pour l’ingrédient suivant, challenge avec hashtag, recettes du mois, créateurs mis en avant, statistiques, partenariats transparents et récompenses soumises à règlement. Aucun classement public fondé seulement sur les likes : la sélection éditoriale et des critères de qualité évitent la fraude et la concentration.

## 14. Ramadan

Ramadan est un temps éditorial majeur, pas une fonctionnalité technique séparée. La première version peut proposer une collection temporaire dans Découvrir et quelques modules feed : ftour, soupes, briouates, plats familiaux, desserts, recettes rapides/économiques et grandes tablées.

Les challenges, sélections quotidiennes, partenaires et contenus sponsorisés sont différés. Les contenus doivent rester inclusifs, géographiquement localisables et jamais imposés à tous les utilisateurs.

`[DECISION FONDATEUR]` Lancement en relation avec Ramadan.

| Choix A | Choix B |
| --- | --- |
| Bêta avant Ramadan avec collection éditoriale testée | Lancement hors événement, puis Ramadan comme premier grand temps fort |
| Avantage : contexte fort, offre éditoriale concentrée | Avantage : temps pour stabiliser feed, publication et modération |
| Inconvénient : pression calendrier et niveau de contenu requis | Inconvénient : occasion locale différée |
| **Recommandation : B** si le MVP n’est pas déjà stabilisé plusieurs semaines avant Ramadan. |

## 15. Maroc, expansion et langues

Casablanca, Rabat, Marrakech, Agadir, Tanger et Fès sont des villes de recrutement créateurs, de curation et de tests. La ville est optionnelle, déclarative et peut enrichir une collection locale ; elle ne doit ni conditionner le feed ni révéler une localisation fine.

L’architecture doit internationaliser les textes d’interface et permettre langues/script RTL, contenu multilingue et recherche par alias. Ne jamais créer une application par langue.

`[DECISION FONDATEUR]` Langues d’interface de lancement.

| Choix A | Choix B |
| --- | --- |
| Français + arabe standard dès V1 ; Darija dans les contenus, anglais ensuite | Français uniquement en interface ; contenus libres dans toutes langues |
| Avantage : plus inclusif au Maroc, prépare RTL | Avantage : exécution, QA et support plus simples |
| Inconvénient : coût traduction/RTL et risque de qualité inégale | Inconvénient : exclusion d’une partie du public cible |
| **Recommandation : A** si une QA arabe/RTL native est disponible ; sinon B temporaire, sans bloquer les légendes arabes/Darija. |

La Darija est prioritairement une langue de contenu et de recherche tolérante aux translittérations ; décider d’une interface complète Darija requiert des tests de compréhension et de ton.

## 16. Contenu externe

Bouf-Bouf ne télécharge, ne recadre, ne re-publie ni n’entraîne automatiquement son feed avec les vidéos de YouTube/Chefclub ou autres tiers sans droit explicite.

Ordre de priorité :

1. vidéos créées directement pour Bouf-Bouf ;
2. créateurs partenaires ayant accordé les droits ;
3. contenus sous licence documentée ;
4. partenariats éditoriaux ;
5. référencement sortant légal avec source et ouverture sur la plateforme d’origine.

Avant ouverture large : registre de droits, procédure de signalement/retrait, règles de musique/images et revue de modération. Le but long terme est un écosystème de contenu propre à Bouf-Bouf.

## 17. MVP strict

### À construire

- feed vertical vidéo, autoplay, swipe et lecture adaptative ;
- comptes et profils publics ;
- publication, traitement vidéo, modération minimale et signalement ;
- like, sauvegarde, follow, commentaire et partage ;
- recette structurée associable à une vidéo ;
- recherche simple de vidéos/créateurs/hashtags/ingrédients ;
- hashtags simples et collections ;
- feed de règles explicables avec sélection éditoriale et signaux comportementaux ;
- Star du Mois éditoriale basique ;
- analytics produit et notifications in-app essentielles.

### Fonctionnalités différées

- marketplace, panier, livraison, commande, réservation et paiement ;
- publicité automatisée, self-service et rémunération créateur ;
- IA/ML avancée, graphe social complexe, messagerie, live, stories ;
- éditeur vidéo avancé, duos/remix/effets et bibliothèque musicale ;
- concours avec prix, systèmes de points, classement compétitif public ;
- profil professionnel complet, menu transactionnel, promotions automatisées ;
- taxonomie dense et dizaines de filtres/collections.

## 18. KPI

Le KPI produit principal doit répondre à : **« Les personnes reviennent-elles spontanément scroller de la cuisine ? »**

**KPI nord : rétention D7 des utilisateurs ayant consommé au moins trois vidéos lors de leur première session**, complétée par sessions actives récurrentes. Il mesure le retour volontaire et ne récompense pas seulement l’acquisition.

| Famille | KPI |
| --- | --- |
| audience | DAU, WAU, MAU, nouveaux utilisateurs activés |
| rétention | D1, D7, D30, sessions/utilisateur, jours actifs |
| feed | durée moyenne session, vidéos vues/session, taux de skip, complétion, relecture |
| utilité | ouvertures de recette, sauvegardes/vue, réouvertures de sauvegardes |
| social | likes, follows, commentaires, partages par vidéo/vue |
| offre | vidéos publiées, créateurs actifs, délai jusqu’à première publication |
| qualité | échecs lecture/upload, signalements/1 000 vues, délai de traitement modération |
| Star du Mois | participants, vidéos associées, sauvegardes et rétention des visiteurs de l’événement |

Événements minimaux : impression, démarrage, 25/50/75/100 % de visionnage, pause, swipe, ouverture recette, like, sauvegarde, follow, partage, recherche, publication et signalement. Leur collecte doit respecter consentement, minimisation et politique de rétention.

## 19. Monétisation future

La V1 ne comporte aucune transaction ni publicité. L’architecture produit doit toutefois réserver des emplacements et états de contenu pour :

| Modèle | Intégration ultérieure |
| --- | --- |
| publicité | insertion explicitement identifiée et plafonnée dans le feed |
| contenu sponsorisé | label visible sur vidéo/profil et politique créateurs |
| partenaires Star du Mois | page événement, avec indépendance éditoriale |
| professionnels payants | options enrichies après preuve de valeur |
| affiliation/Store | liens produits clairement signalés depuis une recette |
| créateurs | programme séparé après contrôles fraude, fiscalité et contrats |

Toute monétisation doit préserver la promesse : ouvrir l’application pour voir de la cuisine intéressante, non pour traverser des offres commerciales.

## 20. Architecture technique et sécurité — contraintes confirmées

Architecture à conserver : Android natif/Kotlin/Jetpack Compose, FastAPI, PostgreSQL, Docker et monolithe modulaire. Vidéos hors base relationnelle : stockage objet, CDN, streaming adaptatif et traitement asynchrone. Les microservices sont exclus du MVP.

Contraintes non négociables : authentification sécurisée, autorisation objet, validation d’entrées, limites de débit, secrets hors dépôt, HTTPS en production, journaux sans données sensibles, suppression de compte, uploads signés avec limites MIME/taille, traitement isolé et signalement/modération.

Aucune contradiction importante avec l’architecture actuelle n’a été identifiée.

## 21. Risques produit

1. Manque de contenu initial de qualité : la boucle feed ne peut convaincre sans créateurs pilotes et curation.
2. Promesse confuse entre réseau social, recettes et marketplace : garder le feed au centre.
3. Coût/performance vidéo sur réseau mobile : bitrate, CDN, limites et observation des coûts indispensables.
4. Problèmes de droits et modération : droits de tiers, musique, contenus inappropriés et retrait rapide.
5. Inscription trop tôt : réduit l’essai du produit ; trop tard sans protection : fragilise les actions sociales.
6. Recommandation répétitive/froide : diversité éditoriale et signaux explicables nécessaires.
7. Recherche multilingue décevante : français, arabe et Darija exigent corpus/test réels.
8. Événements Star du Mois manipulables : différer vote/récompenses avant anti-abus.
9. Trop de fonctions professionnelles trop tôt : support et attentes commerciales détournent du cœur produit.
10. Calendrier Ramadan non maîtrisé : forte opportunité mais pression de contenu et de stabilité.

## 22. Décisions restant à prendre

### [DECISION FONDATEUR] 1 — Public prioritaire de lancement

| Choix A | Choix B |
| --- | --- |
| consommateurs en quête d’inspiration du soir | créateurs culinaires comme cible première |
| Avantage : répond à la promesse, audience large | Avantage : concentre l’effort sur l’offre de contenu |
| Inconvénient : nécessite contenu initial solide | Inconvénient : risque de faible demande sans public |
| **Recommandation : A**, avec recrutement parallèle de créateurs pilotes. |

### [DECISION FONDATEUR] 2 — Périmètre géographique de bêta

| Choix A | Choix B |
| --- | --- |
| Casablanca pilote, puis autres villes | Maroc multi-ville dès la bêta |
| Avantage : curation/support et apprentissage rapides | Avantage : diversité de contenu et audience plus large |
| Inconvénient : perception locale limitée | Inconvénient : opération/modération plus diffusés |
| **Recommandation : A**, sauf portefeuille de créateurs déjà réparti. |

### [DECISION FONDATEUR] 3 — Authentification de lancement

| Choix A | Choix B |
| --- | --- |
| téléphone + e-mail | e-mail + Google/Apple selon plateforme |
| Avantage : ancrage local, anti-abus potentiel | Avantage : mise en œuvre/UX souvent plus simple |
| Inconvénient : coût SMS, récupération et confidentialité | Inconvénient : moindre contrôle anti-abus/local |
| **Recommandation :** décider après analyse des coûts, règles stores et niveau anti-abus requis ; laisser regarder sans compte dans tous les cas. |

### [DECISION FONDATEUR] 4 — Langues d’interface

Voir section 15. Recommandation conditionnelle : français + arabe standard si QA RTL native ; sinon français temporaire et plan de localisation daté.

### [DECISION FONDATEUR] 5 — Standard de recette

Voir section 10. Recommandation : recette structurée obligatoire pour le type « recette », facultative pour astuces/découvertes.

### [DECISION FONDATEUR] 6 — Politique de contenu et âge minimum

| Choix A | Choix B |
| --- | --- |
| bêta réservée à adultes/18+ | accès à partir d’un âge plus bas avec protections adaptées |
| Avantage : règles, consentement et modération plus simples | Avantage : audience plus large |
| Inconvénient : exclut de jeunes passionnés | Inconvénient : obligations légales et sûreté accrues |
| **Recommandation : A** pour la première bêta, à valider juridiquement pour chaque marché. |

### [DECISION FONDATEUR] 7 — Prestataire vidéo et budget

| Choix A | Choix B |
| --- | --- |
| service vidéo géré (lancement rapide) | stockage objet + pipeline vidéo géré en interne |
| Avantage : vitesse, streaming et analytics opérés | Avantage : contrôle et optimisation long terme |
| Inconvénient : coût unitaire/dépendance | Inconvénient : opération, sécurité et délai de développement |
| **Recommandation : A** pour la V1 si le budget le permet ; réévaluer avec la traction. |

### [DECISION FONDATEUR] 8 — Star du Mois communautaire

Voir section 13. Recommandation : lancement éditorial seulement puis vote lorsque la sûreté est prête.

### [DECISION FONDATEUR] 9 — Statut des professionnels

Voir section 12. Recommandation : après validation de la boucle consommateur/créateur, sauf partenariats pilotes apportant du contenu distinctif.

### [DECISION FONDATEUR] 10 — Seuil de préparation bêta

| Choix A | Choix B |
| --- | --- |
| lancer avec un corpus éditorial pilote contrôlé | attendre une grande bibliothèque de contenu avant test |
| Avantage : apprentissage plus tôt, coût initial limité | Avantage : feed plus riche dès le premier jour |
| Inconvénient : risque d’offre insuffisante | Inconvénient : retarde les apprentissages réels |
| **Recommandation : A**, avec un seuil à définir : diversité de thèmes, créateurs actifs, modération et fiabilité vidéo prêtes. |

## 23. Ordre recommandé des prochaines étapes

1. Le fondateur valide les dix décisions de la section 22 et les choix marqués dans ce document.
2. Définir la politique de contenu, droits, modération, confidentialité et âge de bêta avec conseil compétent.
3. Sélectionner le prestataire vidéo, l’authentification et le budget opérationnel ; verrouiller les contraintes techniques associées.
4. Établir une stratégie de contenu pilote : créateurs, droits, curation et premières collections Maroc/« Comme chez nous ».
5. Produire un prototype UX cliquable des parcours : feed, recette, sauvegarde, publication et Star du Mois ; le tester avec utilisateurs et créateurs marocains.
6. Transformer le MVP validé en backlog priorisé avec critères d’acceptation, contrats d’API et plan de données/analytics.
7. Définir environnements, CI, observabilité, sécurité applicative et plan de test vidéo avant les écrans fonctionnels.
8. Construire le socle compte/profil/média/traitement et le parcours feed minimal en tranches testables.
9. Ajouter les interactions sociales, recette, recherche simple et modération minimale ; instrumenter tous les KPI essentiels.
10. Lancer une bêta fermée, analyser D1/D7, qualité feed/contenu et coûts vidéo ; itérer avant acquisition ou monétisation.

---

## Annexe — les 10 validations indispensables avant tout développement MVP

1. Public principal de lancement et seuil de succès bêta.
2. Ville/périmètre de la bêta Maroc.
3. Langues et niveau réel de support arabe/RTL/Darija.
4. Authentification et politique de compte.
5. Standard minimum de recette structurée.
6. Politique contenu, droits, modération et âge minimum.
7. Fournisseur/pipeline vidéo et budget de diffusion.
8. Rôle initial de Star du Mois (éditorial ou vote).
9. Inclusion ou report des profils professionnels.
10. Corpus de contenu/partenariats nécessaires avant ouverture bêta.
