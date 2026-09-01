# Bouf-Bouf

Le réseau social de la cuisine — pensé pour démarrer au Maroc.

Ce dépôt contient le socle technique de la V1 : une application Android native et une API REST. La V1 valide la boucle **regarder → découvrir → s'inspirer → liker → enregistrer → suivre → publier**.

## Démarrage

1. Copier `backend/.env.example` vers `backend/.env` et définir des secrets propres.
2. Lancer les services : `docker compose up --build`.
3. Vérifier l'API : `http://localhost:8000/api/v1/health`.

La documentation produit et l'architecture se trouvent dans [`docs/architecture-v1.md`](docs/architecture-v1.md).

## Structure

- `android/` — application Kotlin / Jetpack Compose (à initialiser dans Android Studio).
- `backend/` — API FastAPI, domaine métier et migrations à venir.
- `infra/` — configuration d'infrastructure locale.
- `docs/` — décisions et documentation d'architecture.

## Principes

- API versionnée dès le premier endpoint.
- Authentification par jetons courts et rotation de refresh tokens (à implémenter avant les comptes réels).
- Vidéos stockées hors de PostgreSQL, dans un stockage objet compatible S3.
- Secrets exclusivement fournis par l'environnement ou un gestionnaire de secrets.
