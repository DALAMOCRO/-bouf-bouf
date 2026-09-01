# Application Android

Application Kotlin / Jetpack Compose de Bouf-Bouf. Le package de démonstration est `com.boufbouf.app` ; il devra être confirmé avant publication sur le Play Store.

## Feed V1

Le premier écran fonctionnel est le feed vertical : données culinaires mockées, swipe plein écran, Media3/ExoPlayer, interactions locales et page recette démo. Le dépôt `FeedRepository` isole ces données afin qu'une implémentation `GET /api/v1/feed` les remplace sans réécrire l'interface.

Les URLs vidéo sont des médias de démonstration hébergés pour Media3 par Google ; elles ne sont pas du contenu tiers republié. Elles devront être remplacées par du contenu culinaire dont Bouf-Bouf possède les droits avant toute bêta utilisateur.

## Ouvrir et tester

Ouvrir le dossier `android/` dans Android Studio avec un JDK 17. Exécuter les tests unitaires avec :

```text
gradle :app:testDebugUnitTest
```

Les tests instrumentés Compose requièrent un émulateur/appareil Android :

```text
gradle :app:connectedDebugAndroidTest
```

## Conventions

- minSdk 26 ; architecture MVVM feature-first ; Media3 ExoPlayer.
- aucun secret ni clé de service dans l'APK.
- futurs environnements `debug`, `staging`, `release` et URLs API injectées par build variant.
- les fonctionnalités hors feed/recette démo restent volontairement absentes à cette étape.
