# stock-feed-java

A small Spring Boot application that connects to a third-party WebSocket stock feed provider, subscribes to tickers, and persists incoming trade/quote/bar messages into MongoDB collections.

This project demonstrates a lightweight streaming consumer architecture using:

- Java 21 (toolchain configured in `build.gradle`)
- Spring Boot 3 (web, websocket, and data-mongodb starters)
- MongoDB for persisting Trades, Quotes, and Bars
- A WebSocket client that authenticates with the provider and sends a subscription request

## Highlights

- Main entry: `com.shiva.stockfeed.StockFeedApplication` — the app creates a `StockFeedProviderWSClient` that connects to the provider on startup.
- WebSocket handling: `com.shiva.stockfeed.handler.WebSocketHandler` decodes incoming JSON messages and routes them to a `FeedHandler` implementation.
- Persistence: `FeedHandlerImpl` writes messages to MongoDB repositories:
  - `Trades` collection — `StockTradeMessage`
  - `Quotes` collection — `StockQuoteMessage`
  - `Bars` collection — `StockBarMessage`
- Health endpoints:
  - `GET /` returns `UP`
  - `GET /live` returns `live`

## Prerequisites

- Java 21
- Gradle (wrapper is included, `./gradlew` is recommended)
- A running MongoDB instance (connection URI will be provided via environment variables)
- Access credentials and WebSocket URL from your feed provider

## Configuration

The application reads configuration from `src/main/resources/application.properties`. Important properties (provided via environment variables) are:

- `MONGODB_URL` — MongoDB connection URI (set to `spring.data.mongodb.uri`)
- `MONGODB_DATABASE_FEED` — MongoDB database name
- `WS_URL` — WebSocket URL of the feed provider (`feed-provider.wsUrl`)
- `API_KEY` — API key value (`feed-provider.key`)
- `API_KEY_HEADER` — header name used to send the API key (`feed-provider.keyName`)
- `API_SECRET` — API secret value (`feed-provider.secret`)
- `API_SECRET_HEADER` — header name used to send the API secret (`feed-provider.secretName`)
- `TICKERS` — space-separated list of tickers to subscribe to (example: `AAPL TSLA MSFT`)

Example environment variables (macOS / Linux / zsh):

```bash
export MONGODB_URL="mongodb://localhost:27017"
export MONGODB_DATABASE_FEED="stockfeed"
export WS_URL="wss://feed-provider.example/ws"
export API_KEY="your-api-key"
export API_KEY_HEADER="X-API-KEY"
export API_SECRET="your-api-secret"
export API_SECRET_HEADER="X-API-SECRET"
export TICKERS="AAPL TSLA MSFT"
```

Notes:
- The application constructs the subscription request after a successful WebSocket authentication message. It uses `TICKERS` (space-separated) and converts them to uppercase.
- `spring.data.mongodb.pool.*` properties in `application.properties` control the MongoDB driver pool settings.

## Build and run

Run with the Gradle wrapper (recommended):

```bash
# Run the application (starts Spring Boot and connects to the WS provider)
./gradlew bootRun

# Or build a jar and run it
./gradlew build
java -jar build/libs/stock-feed-java-0.0.1-SNAPSHOT.jar
```

If you want to skip tests during the Gradle `test` task, set the `SKIP_TESTS` environment variable before running Gradle:

```bash
export SKIP_TESTS=1
./gradlew build
```

## Docker

This repository includes a `Dockerfile`. Example build and run commands:

```bash
# build image
docker build -t stock-feed-java:latest .

# run container (provide env vars for configuration)
docker run -e MONGODB_URL="$MONGODB_URL" \
  -e MONGODB_DATABASE_FEED="$MONGODB_DATABASE_FEED" \
  -e WS_URL="$WS_URL" \
  -e API_KEY="$API_KEY" \
  -e API_KEY_HEADER="$API_KEY_HEADER" \
  -e API_SECRET="$API_SECRET" \
  -e API_SECRET_HEADER="$API_SECRET_HEADER" \
  -e TICKERS="$TICKERS" \
  --rm stock-feed-java:latest
```

## API / Endpoints

Simple health endpoints exposed by the Spring Boot application:

- GET `/` — returns `UP`
- GET `/live` — returns `live`

This app primarily acts as a consumer of a WebSocket feed and does not expose additional public HTTP APIs for feeds. If you want read endpoints (e.g., to query the stored trades), consider adding REST controllers that use the Mongo repositories (`StockTradeRepository`, `StockQuoteRepository`, `StockBarRepository`).

## Data model and storage

Important message types are defined in `com.shiva.stockfeed.model`:

- `StockTradeMessage` -> stored in `Trades` collection
- `StockQuoteMessage` -> stored in `Quotes` collection
- `StockBarMessage` -> stored in `Bars` collection

Messages follow the incoming provider JSON (Jackson annotations map abbreviated field names like `T`, `S`, `p`, `s`, etc.). The `MessageType` enum maps provider type codes to internal values and indicates whether a type should be persisted.

## Tests

Run tests with:

```bash
./gradlew test
```

The test task respects the `SKIP_TESTS` environment variable if set.

## Troubleshooting

- If the application fails to connect to MongoDB, verify `MONGODB_URL` and network access.
- If no messages are persisted, enable logging or inspect stdout — `WebSocketHandler` prints incoming payloads to the console.
- Ensure the `API_KEY_HEADER` and `API_SECRET_HEADER` match what the provider expects; those are used in the initial WebSocket handshake headers.

## Next steps / Improvements

- Add REST endpoints to query stored Trades/Quotes/Bars.
- Add resilient reconnect/backoff logic for the WebSocket client.
- Add structured logging and metrics (Micrometer) for operational visibility.
- Add integration tests that mock the WebSocket provider and MongoDB (Testcontainers)

## License

This project does not include an explicit license. Add a `LICENSE` file if you want to make the project's license explicit.