# UltraTrackStats

**UltraTrackStats** est un plugin Bukkit/Spigot (1.21.4) qui fournit un système centralisé et ouvert de suivi des statistiques joueurs pour serveurs Minecraft. Il est conçu comme une infrastructure partagée — n'importe quel plugin sur votre serveur peut lire et écrire des stats joueurs via son API publique.

**Auteur :** FurranyStudio (Maxime66410)  
**Version :** 1.0.0  
**Version API :** 1.21.4  

---

## Ce qu'il fait

- Persiste les statistiques joueurs (kills, blocs cassés, blocs posés, et toute stat personnalisée) dans une base de données SQLite locale
- Expose une API publique simple (`StatsAPI`) pour que d'autres plugins puissent récupérer, ajouter, définir ou réinitialiser n'importe quelle stat
- Déclenche un événement Bukkit personnalisé (`StatUpdateEvent`) à chaque modification de stat, permettant à d'autres plugins de réagir en temps réel
- Suit optionnellement les cassages de blocs, les poses de blocs et les kills de mobs automatiquement via des listeners intégrés

---

## Index de la documentation

| Fichier | Contenu |
|---|---|
| [SETUP.md](SETUP.md) | Installation, prérequis et configuration |
| [COMMANDS.md](COMMANDS.md) | Commandes en jeu et permissions |
| [API.md](API.md) | Utilisation de l'API publique avec exemples Java |
| [EVENTS.md](EVENTS.md) | `StatUpdateEvent` — écouter les changements de stats |
| [DATABASE.md](DATABASE.md) | Schéma SQLite et conventions des IDs de stats |
