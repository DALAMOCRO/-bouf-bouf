# Bouf-Bouf V1 — Conception produit et expérience utilisateur

## 1. Product vision

**Bouf-Bouf aide à décider quoi cuisiner maintenant, par l'inspiration vidéo plutôt que par une recherche laborieuse.**

La promesse utilisateur est : *« Je ne sais pas quoi cuisiner ? Ouvre Bouf-Bouf. »* Le produit est un réseau social vidéo, mais la cuisine est sa seule grammaire : chaque vidéo doit inspirer, apprendre, ou rendre un repas plus réalisable.

La V1 doit gagner sur trois critères : trouver une idée en moins de 30 secondes, garder la recette utile après le swipe, et rendre l'envie de publier ou suivre naturelle. La densité de fonctionnalités est secondaire à la clarté du feed.

Principes directeurs :

- inspiration avant intention de recherche ;
- vidéo immersive, information culinaire disponible sans recouvrir la vidéo ;
- une action principale par moment ;
- contenu local pertinent, sans enfermer l'identité du produit dans une seule ville ou langue ;
- transparence : un contenu sponsorisé ou externe est identifiable.

## 2. User personas

| Persona | Situation | Besoin prioritaire | Frein | Valeur V1 |
| --- | --- | --- | --- | --- |
| **Sara, 27 ans, active à Casablanca** | rentre tard, cuisine pour une ou deux personnes | idée simple, rapide et économique | ne veut pas lire de longues recettes | feed immédiat, filtres légers, sauvegarde |
| **Youssef, 22 ans, étudiant à Rabat** | cuisine avec un budget/placard limité | recettes accessibles et techniques de base | manque d'ingrédients ou de confiance | ingrédients visibles, temps/difficulté, créateurs proches |
| **Khadija, 40 ans, cuisinière familiale à Fès** | cherche à varier les repas du foyer | inspiration fiable mêlant cuisine marocaine et internationale | interface trop chargée ou langage excluant | contenus en langue choisie, recettes structurées, sauvegardes |
| **Amine, 29 ans, créateur culinaire à Marrakech** | publie déjà des recettes | audience pertinente et outils de publication simples | démarrer avec zéro audience | profil, hashtags, Star du Mois, statistiques simples plus tard |
| **Traiteur local (futur)** | veut être découvert sans gérer une marketplace | montrer sa cuisine et être contacté | système commercial lourd | profil professionnel léger et contact direct |

## 3. User journey prioritaire

```text
Déclic : « Qu'est-ce que je cuisine ? »
        ↓
Ouvrir → voir immédiatement une vidéo qui joue → swiper 1–5 fois
        ↓
Sauvegarder une idée ou ouvrir sa recette → décider de cuisiner
        ↓
Suivre un créateur / revenir à ses sauvegardes → rétention
        ↓
Plus tard : publier une vidéo → devenir créateur
```

Le premier succès n'est pas l'inscription : c'est un **signal d'inspiration**, par exemple une sauvegarde, une recette ouverte, ou une vidéo terminée. L'inscription est demandée au moment où elle préserve une valeur personnelle (sauvegarder, suivre, commenter, publier), pas avant de voir le produit.

### Parcours de première visite

1. Splash très bref ; chargement du contenu éditorial initial.
2. Onboarding de trois écrans maximum ; possibilité de passer.
3. Choix de préférences optionnel mais recommandé, puis feed immédiatement.
4. Le visiteur peut regarder et rechercher ; il doit s'identifier pour sauvegarder, suivre, commenter ou publier.
5. Après une interaction, la connexion reprend exactement sur la vidéo et l'action demandée.

## 4. Information architecture

La navigation basse V1 contient cinq destinations :

```text
Accueil / Feed     Découvrir     Publier     Sauvegardes     Profil
```

- **Accueil** : feed « Pour vous » et accès discret à « Abonnements ».
- **Découvrir** : recherche, hashtags, ingrédients, catégories, Star du Mois.
- **Publier** : création vidéo (connexion obligatoire).
- **Sauvegardes** : collection privée (connexion obligatoire).
- **Profil** : profil personnel ou créateur, réglages et accès aux notifications.

Les notifications sont une destination secondaire via une icône en haut du feed/profil, afin de préserver la barre basse. Les pages vidéo, hashtags, Star du Mois et profils d'autrui sont des écrans poussés dans la pile de navigation, avec retour explicite.

## 5. Navigation map

```text
Splash → Onboarding → Préférences ───────────────┐
                    └──────────────→ Feed ←──────┘
                                         ├→ Vidéo / Recette
                                         ├→ Profil créateur → Suivre / vidéos
                                         ├→ Notifications
                                         └→ Connexion (action protégée)
Feed ↔ Découvrir → Recherche → Hashtag / Ingrédient → Vidéo
                    └→ Star du Mois → Vote / Challenge / vidéos
Feed ↔ Sauvegardes → Vidéo
Feed ↔ Publier → Authentification → Capture / import → Édition → Publication
Feed ↔ Profil → Modifier profil / Mes vidéos / Paramètres
                    └→ Activer profil pro (futur)
```

## 6. Wireframes textuels des écrans

### 1. Splash screen

**Objectif** : établir la marque et préparer une transition instantanée vers le contenu.

```text
┌─────────────────────────────┐
│                             │
│          Bouf-Bouf          │
│     L'inspiration cuisine   │
│                             │
│           [loader]          │
└─────────────────────────────┘
```

- **Visible** : logo, fond de marque, indicateur discret uniquement si le chargement dépasse un court délai.
- **Interactions/navigation** : aucune ; redirige vers onboarding, préférences ou feed selon l'état local.
- **Données** : session, langue, préférences, configuration éditoriale minimale.
- **Visiteur/connecté** : même rendu ; un connecté retrouve directement le feed.

### 2. Onboarding

**Objectif** : faire comprendre la promesse, sans créer un tunnel de conversion.

```text
┌─────────────────────────────┐
│ Passer                      │
│     [illustration vidéo]    │
│ Une idée pour ce soir.      │
│ Swipez, inspirez-vous,      │
│ cuisinez.                   │
│                 ● ○ ○       │
│ [Continuer]                 │
└─────────────────────────────┘
```

- Trois messages : découvrir, sauvegarder, partager/publier. Un seul bouton « Continuer », « Passer » permanent.
- Ne demander ni compte ni permissions système ici.
- **Visiteur** : vu une seule fois sauf réinitialisation. **Connecté** : ignoré.

### 3. Inscription / connexion

**Objectif** : permettre les actions personnelles avec le moins de friction possible.

```text
┌─────────────────────────────┐
│ ← Connexion                 │
│ Gardez vos idées cuisine.   │
│ [Continuer avec téléphone]  │
│ [Continuer avec e-mail]     │
│ [Google] [Apple si requis]  │
│ Déjà un compte ? Connexion  │
│ Conditions et confidentialité│
└─────────────────────────────┘
```

- Les fournisseurs exacts restent à décider. Inscription progressive : handle et profil complétés après vérification.
- Depuis une action protégée, conserver le contexte puis reprendre cette action après succès.
- **Visiteur** : peut fermer et revenir au feed. **Connecté** : cet écran est inaccessible sauf ajout de compte/gestion session.

### 4. Choix initial des préférences culinaires

**Objectif** : obtenir des signaux froids sans contraindre l'utilisateur.

```text
┌─────────────────────────────┐
│ Qu'aimez-vous cuisiner ?    │
│ [Marocain] [Rapide] [Petit  │
│ budget] [Végétarien] ...    │
│ Langues : [Français] [عربية]│
│ Ville (facultatif) [Casa ▾] │
│ [Voir mon feed]  Passer     │
└─────────────────────────────┘
```

- Catégories limitées (8–12), restrictions/allergies facultatives et clairement séparées des goûts ; ne pas déduire de données sensibles.
- Ville facultative, utilisée pour éditorial/local et jamais comme filtre bloquant.
- **Visiteur** : préférences stockées localement et migrées après création du compte. **Connecté** : synchronisées et modifiables dans réglages.

### 5. Feed principal

**Objectif** : provoquer immédiatement l'inspiration, en plein écran.

```text
┌─────────────────────────────┐
│ Pour vous   Abonnements  ◉  │
│                             │
│                             │
│        VIDÉO 9:16           │
│          [auto-play]        │
│                             │
│ @chefamina       ♡  2,4 k   │
│ Couscous express 20 min     │
│ #couscous #rapide           │
│ [Voir recette]   ⌁  ↗  ⋮    │
│                 🔖          │
└─────────────────────────────┘
```

- Détail complet en section 7. L'onglet par défaut est « Pour vous » ; « Abonnements » donne un feed chronologique des comptes suivis.
- **Visiteur** : regarde, swipe, recherche et partage. Like/sauvegarde/follow/commentaire/publier demandent l'identification. **Connecté** : toutes les actions sont directes ; leur état est optimiste et réconcilié.

### 6. Page vidéo détaillée / recette

**Objectif** : transformer l'inspiration en repas réalisable sans perdre la vidéo.

```text
┌─────────────────────────────┐
│ ←  Vidéo                     │
│ [lecteur, réduit si scroll] │
│ Couscous express             │
│ 20 min · facile · 2 portions│
│ [♡] [🔖 Sauvegarder] [↗]   │
│ Ingrédients ▾               │
│ Étapes ▾                     │
│ Commentaires (32)            │
└─────────────────────────────┘
```

- Vidéo, créateur, titre, durée/temps de préparation, difficulté facultative, ingrédients structurés, étapes, hashtags, commentaires et signalement.
- « Voir recette » depuis le feed ouvre ici ; les sections se replient pour éviter la surcharge.
- **Visiteur** : lecture et consultation ; actions sociales demandent connexion. **Connecté** : commenter, sauvegarder et suivre sans sortir de l'écran.

### 7. Profil utilisateur

**Objectif** : gérer son identité, retrouver ses publications et ses interactions publiques.

```text
┌─────────────────────────────┐
│ ☰                  🔔       │
│ [avatar] @sara              │
│ 12 abonnés · 8 abonnements  │
│ [Modifier le profil]        │
│ Vidéos   Aimées*            │
│ [grille de vidéos]          │
└─────────────────────────────┘
```

- Avatar, nom, bio, compteurs, grille des vidéos. Les vidéos aimées sont privées par défaut (et peuvent être omises entièrement en V1).
- « Modifier » mène à nom, handle, bio, avatar, langue et préférences ; réglages incluent confidentialité et déconnexion.
- **Visiteur** : un profil non connecté redirige vers connexion. **Connecté** : voit son propre profil et ses réglages.

### 8. Profil créateur

**Objectif** : convertir un spectateur intéressé en abonné et donner confiance.

```text
┌─────────────────────────────┐
│ ←                            │
│ [avatar] Chef Amina  ✓?     │
│ @chefamina · Casablanca      │
│ 12 k abonnés · 230 vidéos    │
│ Recettes marocaines rapides  │
│ [Suivre]  [↗]               │
│ Vidéos   Recettes            │
│ [grille]                     │
└─────────────────────────────┘
```

- Bio, localisation déclarative facultative, compteurs, bouton follow, partage, grille de vidéos ; badge seulement avec un programme de vérification défini.
- **Visiteur** : peut explorer et partager ; suivre déclenche authentification. **Connecté** : follow immédiat ; profil public identique.

### 9. Publication d'une vidéo

**Objectif** : publier une recette ou astuce sans outil de montage complexe.

```text
Choisir/capturer → Découper et couverture → Décrire → Publier
```

- Import/caméra, durée maximale définie, aperçu, découpe simple, couverture, légende, hashtags, recette structurée facultative, visibilité (publique/brouillon) et acceptation des droits.
- Pas d'éditeur musical propriétaire, effets complexes, duos, livestream ou collage en V1.
- **Visiteur** : connexion d'abord. **Connecté** : brouillon local ; upload avec progression, reprise et état « traitement en cours ».

### 10. Recherche

**Objectif** : répondre à une intention après ou avant l'inspiration.

```text
┌─────────────────────────────┐
│ ← [Rechercher une recette]  │
│ Tendances · Ingrédients      │
│ [tomate] [poulet] [rapide]  │
│ Résultats : Vidéos Créateurs │
│ [cartes vidéo]               │
└─────────────────────────────┘
```

- Suggestions de termes, ingrédients, hashtags et créateurs ; résultats vidéos par défaut. Recherche tolérante aux variantes arabe/darija/français à concevoir.
- **Visiteur/connecté** : utilisation identique. Les recherches servent de signal, avec consentement analytics.

### 11. Hashtags

**Objectif** : explorer une intention commune sans obliger à connaître des créateurs.

```text
┌─────────────────────────────┐
│ ← #recettemarocaine          │
│ 18,2 k vidéos                │
│ [Suivre le sujet]*           │
│ À la une · Récentes          │
│ [grille/liste de vidéos]     │
└─────────────────────────────┘
```

- Titre normalisé, volume, sélection éditoriale et résultats. Suivi de sujet est différé si cela complexifie les subscriptions ; un clic et une sauvegarde de vidéo suffisent en V1.
- **Visiteur** : consulte. **Connecté** : suit le hashtag seulement si la fonctionnalité est retenue ; sinon même comportement.

### 12. Vidéos sauvegardées

**Objectif** : faire revenir l'utilisateur au moment de cuisiner.

```text
┌─────────────────────────────┐
│ Sauvegardes                  │
│ Toutes  · À cuisiner         │
│ [vidéo] [vidéo] [vidéo]      │
│ Créez une collection*        │
└─────────────────────────────┘
```

- Grille privée, dernière sauvegarde en premier ; filtre « Toutes » / « À cuisiner ». Les collections nommées sont à différer si elles ne sont pas nécessaires à la bêta.
- **Visiteur** : ouverture de connexion avec explication claire. **Connecté** : sauvegarde/désauvegarde synchronisée.

### 13. Notifications

**Objectif** : faire revenir vers les relations et contenus pertinents, sans pression.

- Liste chronologique groupée : nouveaux abonnés, likes/commentaires sur ses vidéos, réponse à commentaire, validation/refus de publication, rappel Star du Mois opt-in.
- Chaque ligne mène au profil, à la vidéo ou au commentaire concerné. Badge non lu ; contrôles par type et push optionnel.
- **Visiteur** : aucune notification ; l'icône peut proposer de créer un compte. **Connecté** : centre de notifications et préférences.

### 14. Star du Mois

**Objectif** : créer un rendez-vous éditorial, social et saisonnier, en complément du feed.

```text
┌─────────────────────────────┐
│ STAR DU MOIS · Août          │
│           TOMATE             │
│ 18 jours restants             │
│ [Voter pour septembre]       │
│ À la une | Rapide | Maroc    │
│ [Relever le challenge]       │
│ [vidéos, créateurs, recettes]│
└─────────────────────────────┘
```

- Détail en section 8. Accessible depuis Découvrir et via un module rare dans le feed, jamais en interstitiel bloquant.
- **Visiteur** : consulte, et peut être invité à voter/participer après connexion. **Connecté** : vote unique, publication au challenge et interactions.

### 15. Profil professionnel futur

**Objectif** : permettre à un restaurant/traiteur de se présenter, sans transformer V1 en marketplace.

```text
┌─────────────────────────────┐
│ [logo] Traiteur Mohamed      │
│ Casablanca · 4,8 ★*          │
│ [Suivre] [WhatsApp] [Appeler]│
│ Présentation · Horaires      │
│ Menu (lien/PDF)*             │
│ Promotions* · Vidéos         │
└─────────────────────────────┘
```

- V1 éventuelle : identité, zone/localisation, contact, bio et vidéos. Menu, promotions, rating et statistiques sont des extensions et demandent règles, vérification et consentement.
- **Visiteur** : consulter et lancer un contact externe. **Connecté** : suivre/partager ; aucune transaction native.

## 7. Feed UX détaillé

### Composition et hiérarchie

La vidéo remplit l'écran au format 9:16. Un voile dégradé bas rend les informations lisibles sans encadrer tout le contenu. En haut : « Pour vous / Abonnements » et notifications. À droite : avatar du créateur (ouvre son profil), like, commentaires, sauvegarde, partage. En bas à gauche : handle, légende limitée à deux lignes avec « plus », hashtags et métadonnées compactes. Le bouton « Voir recette » est le seul CTA persistant ; il n'apparaît que si la recette est structurée.

Ingrédients : ne pas afficher une longue liste sur le feed. Afficher au maximum trois ingrédients repères, par exemple « Poulet · Citron · Olives », dans une puce au-dessus de « Voir recette » ; le détail appartient à la page recette. Le temps total et le niveau (s'il est fourni) se trouvent sur une seule ligne, par exemple « 20 min · Facile ».

### Gestes et lecture

- Swipe vertical : passe à la vidéo suivante/précédente ; une seule vidéo active.
- Tap sur vidéo : pause/reprise ; un symbole très bref confirme l'action.
- Appui long : menu discret « Pas intéressé », « Signaler », « Copier le lien » ; pas de téléchargement source.
- Autoplay quand au moins 70 % de la carte est visible ; pause en arrière-plan, à l'ouverture d'une page ou quand l'audio est interrompu.
- Préchargement de la vidéo suivante seulement, avec qualité adaptative au réseau et réglage économie de données.
- Like peut être un double tap, mais l'icône reste l'action explicite ; sauvegarde ne doit pas être cachée.

### États

- Chargement : aperçu/miniature et squelette minimal, pas un écran blanc.
- Réseau faible : qualité réduite, bouton réessayer ; ne pas bloquer le feed complet.
- Feed épuisé : proposer de rafraîchir ou découvrir un hashtag, jamais une boucle artificielle de mêmes vidéos.
- Contenu indisponible : passer au suivant et noter sobrement qu'il n'est plus disponible.

### CTA et anti-surcharge

Le CTA primaire est contextuel : « Voir recette » pour une recette, « Voir l'astuce » pour une technique, ou rien si la vidéo se suffit. Aucun bouton d'achat, concours, restaurant ou publicité dans la V1 du feed. Les invites à s'inscrire arrivent seulement après une action à forte intention.

## 8. Star du Mois détaillée

### Structure de l'événement

Un ingrédient est actif pendant un mois civil. Une équipe éditoriale établit une liste courte à partir de saisonnalité, disponibilité locale, prix perçu, tendances de recherche et retours communauté. Le vote communautaire départage ou oriente le mois suivant ; il ne doit pas rendre le choix incohérent avec l'approvisionnement réel.

### Écran et contenu

- Hero : ingrédient, période, bénéfice/angle éditorial et bouton de vote.
- Onglets : « À la une », « Recettes », « Astuces », « Challenge », « Créateurs ».
- Filtres simples : rapide, économique, marocain, international, végétarien ; pas de taxonomie exhaustive.
- Chaque recette affiche sa relation avec l'ingrédient de façon explicite.

### Vote

- Une fenêtre de vote, par exemple la dernière semaine du mois ; un vote par compte vérifié, modifiable jusqu'à la clôture.
- Afficher candidats, description et raison éditoriale (« en saison », « très disponible »), mais ne pas afficher des résultats temps réel s'ils favorisent la manipulation.
- Les visiteurs voient le vote ; ils se connectent pour voter. Un contrôle anti-abus est indispensable avant prix ou concours.

### Challenge, classement et créateurs

Un challenge est facultatif et lancé seulement quand l'équipe peut modérer. Le créateur publie avec un hashtag officiel et coche l'ingrédient. La page distingue : sélection éditoriale, nouveautés et créations de la communauté. Un « classement » V1 doit être une vitrine de créations remarquées, non un classement public basé uniquement sur les likes ; il évite les dynamiques de fraude et favorise les nouveaux créateurs.

Les statistiques internes mesurent vidéos publiées, créateurs participants, vues complètes, sauvegardes et recettes consultées. Les créateurs voient plus tard leur performance agrégée, pas les données individuelles des spectateurs.

### Monétisation future

Une marque ou un producteur peut être partenaire clairement étiqueté, sans modifier les résultats organiques et sans contrôle éditorial caché. Les concours sponsorisés, codes promotionnels et attribution vers une boutique attendent des règles légales, une modération et un contrat partenaire.

## 9. Personnalisation explicable V1

Le feed mêle un socle éditorial de qualité et un classement par signaux. Aucun modèle IA complexe n'est requis.

| Signal | Effet simple sur le feed | Garde-fou |
| --- | --- | --- |
| vidéo regardée et durée | privilégie thèmes/créateurs similaires si visionnage élevé | un swipe immédiat pèse peu |
| complétion/relecture | signal fort d'intérêt pour format, sujet et rythme | plafonner pour éviter une bulle |
| likes et sauvegardes | hausse d'affinité pour créateur, ingrédient, hashtag/catégorie | une sauvegarde est plus forte qu'un like |
| follows | ajoute les créateurs suivis et des voisins éditoriaux | onglet Abonnements séparé et chronologique |
| hashtags/ingrédients | favorise sujets recherchés ou ouverts | diversité obligatoire |
| langue et région choisies | favorise langue compréhensible et pertinence locale | jamais exclure les contenus internationaux |
| « Pas intéressé » | baisse fortement le sujet/format concerné | offrir une annulation immédiate |

Le premier feed d'un visiteur est une sélection éditoriale diversifiée : recettes marocaines, rapides, économiques, techniques et internationales, éventuellement influencée par les préférences explicites. Le classement doit respecter des quotas de diversité : éviter deux vidéos du même créateur à la suite et mélanger découverte, follows et contenus éprouvés. Les raisons d'un contenu peuvent être rendues visibles plus tard via « Pourquoi cette vidéo ? ».

## 10. Maroc, villes et langues

Le Maroc est un contexte de lancement, pas une restriction de produit. Casablanca, Rabat, Marrakech, Agadir, Tanger et Fès peuvent servir à sélectionner des créateurs pilotes, identifier les recettes et produits de saison, et alimenter des collections éditoriales facultatives (« Créateurs à Casablanca »). Aucune ville ne doit être obligatoire pour regarder, publier ou apparaître dans le feed.

La localisation est opt-in, préférablement choisie manuellement au départ. Les lieux précis d'un domicile ne sont jamais demandés. À l'international, le même mécanisme devient pays/région et non une logique spécifique au Maroc.

Les langues françaises, arabe, Darija et anglaise doivent être une décision de lancement fondée sur les contenus pilotes. Recommandation à valider : interface en français et arabe moderne standard, avec légendes libres en Darija et prise en charge progressive de la recherche par variantes/translittération ; anglais disponible comme préférence de contenu et langue d'interface ultérieure. Il faut tester le ton, les polices, le RTL et les requêtes mixtes avant de promettre une couverture complète.

## 11. Creator journey

```text
Utilisateur → Publie sa première vidéo → Créateur actif → Créateur populaire → Professionnel vérifié
```

| Niveau | Capacités | Critère de passage |
| --- | --- | --- |
| Utilisateur | regarder, sauvegarder, suivre, commenter | compte vérifié + accepte règles de publication |
| Créateur | publier, brouillons, profil, hashtags, recette structurée, Star du Mois | première publication approuvée |
| Créateur actif | historique de vidéos, réponses/commentaires, signaux simples de performance | régularité et respect des règles, non uniquement nombre d'abonnés |
| Créateur populaire | possibilité de sélection éditoriale, éventuels programmes partenaires | critères transparents à définir, contrôles anti-fraude |
| Professionnel | profil enrichi, contact, localisation/horaires, menu éventuel | vérification manuelle de l'entreprise |

La V1 doit donner aux créateurs un statut de publication clair (brouillon, en traitement, publié, retiré avec motif) et des règles de contenu visibles. Le tableau de bord avancé, la monétisation et les outils d'équipe sont différés.

## 12. Professional journey

Un traiteur/restaurant part d'un compte créateur. Après vérification, il peut demander un profil professionnel. La première version enrichit seulement la fiche publique : catégorie, zone, téléphone/WhatsApp, horaires déclarés, présentation et lien menu. Le contact ouvre une application externe ; Bouf-Bouf n'encaisse ni ne gère les commandes.

Des statistiques agrégées futures (vues de profil, clics de contact, sauvegardes) ne sont proposées qu'après politique de confidentialité, mesure fiable et consentement approprié. Promotions doivent être explicitement libellées et soumises à validation éditoriale/modération.

## 13. Stratégie de contenu externe

1. **Contenu propriétaire** : produire ou commander les premières vidéos ; contrats et droits de diffusion clairs.
2. **Créateurs partenaires** : import direct seulement pour des comptes ayant accepté les conditions et attesté leurs droits sur image, recette, musique et médias.
3. **Contenu autorisé** : importer une vidéo lorsqu'une licence écrite ou une licence compatible est enregistrée et vérifiable.
4. **Référencement externe** : une fiche/lien sortant vers YouTube ou une autre plateforme peut inspirer une collection ; afficher la source et ouvrir la plateforme d'origine. Ne pas télécharger, recadrer ni re-publier automatiquement le média.

Un registre interne de droits, une procédure de retrait et une modération minimale sont des prérequis avant l'ouverture à de nombreux créateurs.

## 14. Intégrations business futures

| Modèle | Emplacement futur | Condition avant intégration |
| --- | --- | --- |
| publicité | insertion identifiée dans le feed | fréquence plafonnée, mesure et règles publicité |
| contenu sponsorisé | vidéo/profil avec label explicite | politique créateurs et revue |
| partenaire Star du Mois | page événement et challenge | indépendance éditoriale et contrat |
| professionnels | profil enrichi/abonnement | vérification entreprise et valeur démontrée |
| affiliation | lien recette/produit clairement libellé | transparence, suivi et conformité |
| Store | lien depuis ingrédients/recettes | catalogue, paiement, logistique et support |
| créateurs | programme distinct de performance | prévention fraude, fiscalité et contrats |

## 15. MVP strict

### Construire

- visionnage vertical vidéo, autoplay, swipe et lecture réseau résiliente ;
- feed initial éditorial puis règles simples de personnalisation ;
- comptes, profils publics, follow, like, sauvegarde, commentaire et signalement ;
- publication vidéo avec légende, hashtags, recette/ingrédients facultatifs et pipeline de traitement ;
- recherche de vidéos/créateurs/hashtags et pages hashtag ;
- Star du Mois en vitrine éditoriale, avec vote seulement si la vérification compte est prête ;
- notifications in-app essentielles ;
- règles de contenu, transparence des droits, modération opérationnelle minimale.

### Ne pas construire maintenant

- paiement, livraison, panier, marketplace ou réservation ;
- publicités, self-serve sponsoring ou monétisation créateur ;
- algorithme IA opaque, recommandations sociales complexes, live et messagerie privée ;
- montage vidéo avancé, bibliothèque musicale propriétaire, filtres, duos ou stories ;
- classement compétitif public, concours avec prix, points/jetons ;
- menus transactionnels, promotions automatisées ou système complet restaurant ;
- dizaines de collections, catégories ou filtres.

## 16. KPI à instrumenter dès le lancement

| Domaine | KPI | Question répondue |
| --- | --- | --- |
| activation | % première session avec vidéo vue ≥ 10 s, sauvegarde ou recette ouverte | le feed inspire-t-il ? |
| rétention | D1, D7, D30 par cohorte ; jours actifs | les personnes reviennent-elles ? |
| feed | durée de session, vidéos/session, taux de complétion, skip < 2 s | le contenu tient-il l'attention ? |
| utilité cuisine | ouvertures recette, sauvegardes/visionnage, réouvertures sauvegardes | l'inspiration devient-elle action ? |
| social | follows, likes/commentaires par vidéo, partages | la communauté se forme-t-elle ? |
| créateurs | délai première publication, vidéos publiées/créateur actif, taux de publication réussie | l'offre de contenu peut-elle croître ? |
| qualité/sûreté | signalements pour 1 000 vues, temps de traitement, échecs lecture/upload | le produit est-il fiable et sûr ? |
| Star du Mois | participants, vidéos taguées, vues/sauvegardes de l'événement | le rendez-vous apporte-t-il de la valeur ? |

Événements minimum : impression, début/25/50/75/100 % visionnage, pause, swipe, ouverture recette, like, sauvegarde, follow, recherche, partage, publication et signalement. Les identifiants et durées doivent suivre une politique de consentement et rétention définie avant bêta.

## 17. Risques produit

1. **Feed pauvre au lancement** : recruter un petit groupe de créateurs pilotes et une bibliothèque éditoriale avant acquisition d'utilisateurs.
2. **Promesse floue** : le contenu doit rester cuisine/actionnable ; ne pas dériver vers lifestyle général.
3. **Friction d'inscription** : laisser regarder avant connexion mais protéger correctement actions personnelles.
4. **Contenus répétitifs ou inadaptés** : diversité de feed, règles éditoriales, signalement et modération.
5. **Vidéo coûteuse/lente** : limites de durée, réseau adaptatif, CDN et observation des coûts par minute vue.
6. **Langue/recherche décevantes** : tester sur corpus réel français/arabe/darija plutôt que promettre une recherche universelle.
7. **Fraude au vote/challenge** : différer récompenses et imposer vérification/anti-abus avant compétition.
8. **Droits et confiance** : ne jamais importer du contenu externe sans droit documenté ; fournir retrait rapide.

## 18. Questions à décision du fondateur

1. Quel public de lancement est prioritaire : personnes qui cherchent un dîner rapide, cuisine familiale, ou créateurs culinaires ?
2. Quel est le premier marché géographique réel : Casablanca seulement en pilote ou Maroc multi-ville dès la bêta ?
3. Quelle combinaison d'authentification maximise confiance et conversion (téléphone, e-mail, social) ?
4. Quelles langues d'interface sont réellement supportées au lancement, avec quel niveau de recherche Darija/arabe ?
5. Quelle ligne éditoriale : uniquement recettes réalisables, ou aussi restaurants, dégustation et humour culinaire ?
6. Quelle durée maximale et quelle qualité vidéo sont acceptables compte tenu du budget et du réseau mobile ?
7. Qui choisit/modère la Star du Mois, et quel niveau de vote communautaire est souhaité ?
8. Quel seuil et quelles preuves déclenchent un profil professionnel ou une vérification créateur ?
9. Quelle politique d'âge, de modération et de propriété intellectuelle est acceptable avant une bêta publique ?
10. Quel budget contenu, vidéo et modération est disponible pour les 90 premiers jours ?

Ces réponses doivent guider le prototype UX et le backlog, avant toute implémentation fonctionnelle.
