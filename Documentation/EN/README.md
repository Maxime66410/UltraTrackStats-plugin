# UltraTrackStats

**UltraTrackStats** is a Bukkit/Spigot plugin (1.21.4) that provides a centralized and open player statistics tracking system for Minecraft servers. It is designed as a shared infrastructure component — any plugin on your server can read and write player stats through its public API.

**Author:** FurranyStudio (Maxime66410)  
**Version:** 1.0.0  
**API Version:** 1.21.4  

---

## What it does

- Persists player statistics (kills, blocks broken, blocks placed, and any custom stat) in a local SQLite database
- Exposes a simple public API (`StatsAPI`) so other plugins can get, add, set, or reset any stat
- Fires a custom Bukkit event (`StatUpdateEvent`) every time a stat changes, so other plugins can react in real time
- Optionally tracks block breaks, block placements, and mob kills automatically via built-in listeners

---

## Documentation Index

| File | Content |
|---|---|
| [SETUP.md](SETUP.md) | Installation, requirements, and configuration |
| [COMMANDS.md](COMMANDS.md) | In-game commands and permissions |
| [API.md](API.md) | Public API usage with Java code examples |
| [EVENTS.md](EVENTS.md) | `StatUpdateEvent` — listening for stat changes |
| [DATABASE.md](DATABASE.md) | SQLite schema and stat ID conventions |
