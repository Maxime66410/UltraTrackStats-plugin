# UltraTrackStats

![Version](https://img.shields.io/badge/version-1.0.0-blue)
![Minecraft](https://img.shields.io/badge/minecraft-1.21.4-green)
![License](https://img.shields.io/badge/license-Apache%202.0-orange)

**UltraTrackStats** is a lightweight Bukkit/Spigot plugin designed to act as a **shared statistics backbone** for your server. It tracks player activities — mob kills, blocks broken, and blocks placed — and stores everything in a local SQLite database.

What makes it powerful is its **public API**: any plugin on your server can read or write player stats in just one line of code, and listen to **real-time stat change events** to build rewards, leaderboards, quests, or any other stat-driven feature on top of it.

Built for developers, UltraTrackStats handles the data layer so you don't have to reinvent it in every plugin you write.

UltraTrackStats is **open source** — contributions and feedback are welcome.

---

## Features

- 📦 **Centralized stats** — one database, accessible by all your plugins
- ⚡ **Simple API** — `getStat`, `addStat`, `setStat`, `resetStat`
- 🔔 **Event system** — react to every stat change via `StatUpdateEvent`
- 🧱 **Built-in tracking** — mob kills, block breaks, block placements (toggleable)
- 🗄️ **SQLite storage** — no external database required

---

## Requirements

- Java 21+
- Spigot / Paper 1.21.4

---

## Installation

1. Download the latest `.jar` from the [Releases](../../releases) page
2. Drop it into your server's `plugins/` folder
3. Start or restart your server
4. Configure `plugins/UltraTrackStats/config.yml` as needed

---

## Configuration

```yaml
listeners:
  enabled: false            # Master switch — set to true to activate built-in listeners
  track-block-break: true   # Track blocks broken by players
  track-block-place: true   # Track blocks placed by players
  track-mob-kill: true      # Track mobs killed by players
```

---

## API Usage

Add UltraTrackStats as a dependency in your `plugin.yml`:

```yaml
depend: [UltraTrackStats]
```

Then use the API anywhere in your plugin:

```java
// Get a stat
int kills = StatsAPI.get().getStat(player, "ZOMBIE");

// Add to a stat
StatsAPI.get().addStat(player, "ZOMBIE", 1);

// Set a stat
StatsAPI.get().setStat(player, "LEVEL", 5);

// Reset a stat
StatsAPI.get().resetStat(player, "DEATHS");
```

Listen to stat changes:

```java
@EventHandler
public void onStatUpdate(StatUpdateEvent event) {
    if (!event.getStatId().equals("ZOMBIE")) return;
    Player player = Bukkit.getPlayer(event.getPlayerUUID());
    if (player != null) {
        player.sendMessage("Zombie kills: " + event.getNewValue());
    }
}
```

---

## Commands

| Command | Description | Permission |
|---|---|---|
| `/uts reload` | Reload the configuration | `ultratackstats.reload` |

---

## Documentation

Full documentation is available in the [`Documentation/`](Documentation/) folder:

| | English | Français |
|---|---|---|
| Overview | [EN/README.md](Documentation/EN/README.md) | [FR/README.md](Documentation/FR/README.md) |
| Setup | [EN/SETUP.md](Documentation/EN/SETUP.md) | [FR/SETUP.md](Documentation/FR/SETUP.md) |
| Commands | [EN/COMMANDS.md](Documentation/EN/COMMANDS.md) | [FR/COMMANDS.md](Documentation/FR/COMMANDS.md) |
| API | [EN/API.md](Documentation/EN/API.md) | [FR/API.md](Documentation/FR/API.md) |
| Events | [EN/EVENTS.md](Documentation/EN/EVENTS.md) | [FR/EVENTS.md](Documentation/FR/EVENTS.md) |
| Database | [EN/DATABASE.md](Documentation/EN/DATABASE.md) | [FR/DATABASE.md](Documentation/FR/DATABASE.md) |

---

## License

This project is licensed under the [Apache License 2.0](LICENSE).
