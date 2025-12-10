# AlgaSensors Processor

Data processing worker service. Receives messages from RabbitMQ and processes temperature data from sensors.

## Running

### Development

1. Load environment variables:
   ```bash
   set -a; source .env.dev; set +a
   ```
   (Works in bash/zsh)

2. Start the development server:
   ```bash
   ./gradlew bootRun
   ```

The service runs as a background worker (port 8082 for management endpoints).

### Docker Compose

Start via docker-compose from the root directory:

```bash
docker-compose up algasensors-processor -d
```

## Environment

Key environment variables (see `.env.dev` for all options):

- `SPRING_RABBITMQ_HOST` - RabbitMQ host
- `SPRING_RABBITMQ_PORT` - RabbitMQ port
- `SPRING_RABBITMQ_USERNAME` - RabbitMQ credentials
- `SPRING_RABBITMQ_PASSWORD` - RabbitMQ credentials

## Related Services

- [Manager Service](../manager/README.md) - Device management
- [Monitor Service](../monitor/README.md) - Temperature monitoring
- [Client](../client/README.md) - Web dashboard
