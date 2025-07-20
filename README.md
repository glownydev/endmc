# ZoneManagers

Custom zones, smart rules, SQL saves — all in one Minecraft plugin.

## Features
- Zones stored in SQLite (HikariCP, async)
- Dynamic flags/rules per zone (pvp, build, entry-effect, enter-command, etc.)
- Overlap/priority management
- Inventory GUI for zone management
- Dynamic reload (`/zone reload`)
- Per-zone permissions
- Localized messages (messages.yml)
- Configurable via config.yml

## Installation
1. Build the plugin with Gradle or your IDE.
2. Place the generated `.jar` in your server's `plugins` folder.
3. Start the server. The plugin will create default config and messages files.

## Configuration
- `config.yml`: Database settings, default flags
- `messages.yml`: All plugin messages (localizable)

## Commands
- `/zone` — Open the zone management GUI (admin only)
- `/zone reload` — Reload zones and config from database/files

## Permissions
- `zonemanager.admin` — Access all zone management features
- `zonemanager.edit` — Edit zones in the GUI

## SQL
- Uses SQLite by default (file: `zones.db`)
- Table: `zones (id, name, flags, priority)`

## Extending
- Add new flags/rules in config or database
- Customize messages in `messages.yml`
