# WebGPT

WebGPT is an IntelliJ-based plugin that embeds the ChatGPT web UI in a tool window and provides quick prompts for selected code.

## Architecture

- presentation: UI components and IntelliJ tool window wiring.
- domain: business logic and use cases.
- data: IntelliJ-specific adapters and integrations.

## Features

- Embedded ChatGPT web view.
- Reload/open-in-browser actions.
- Copy prompt for selected code (explain/fix).

## Notes

- Prompts are copied to clipboard.
- If no code is selected, no prompt is copied.

## Execution Conditions

- Supported IDEs: IntelliJ-based IDEs compatible with the configured platform (see build.gradle.kts).
- Minimum IDE version: 2024.1 (sinceBuild 241).
- JCEF must be available and enabled to render the embedded web view.
- Network access to `https://chat.openai.com` is required.
- User must be logged in to ChatGPT in the embedded browser session.

## Running Tests

```bash
./gradlew test
```

Notes:
- UI tests are skipped in headless environments or when JCEF is not supported.

## Local Distribution

Build the plugin locally:

```bash
./gradlew buildPlugin
```

The plugin package is generated in `build/distributions/`. 
Install it in the IDE using "Install Plugin from Disk..." and select the ZIP file.
