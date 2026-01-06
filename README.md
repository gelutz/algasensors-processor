# AlgaSensors Processor

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-6DB33F?logo=springboot&logoColor=white)

Data processing worker service. Receives temperature data via HTTP endpoints and publishes to RabbitMQ for downstream services.

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

The service will be available at `http://localhost:8082`.

### Docker Compose

Start via docker-compose from the root directory:

```bash
docker-compose up processor -d
```

## Environment

Key environment variables (see `.env.dev` for all options):

- `SPRING_APPLICATION_NAME=processor` - Service name
- `SERVER_PORT=8082` - HTTP port
- `SPRING_RABBITMQ_HOST` - RabbitMQ host
- `SPRING_RABBITMQ_PORT` - RabbitMQ port
- `SPRING_RABBITMQ_USERNAME` - RabbitMQ credentials
- `SPRING_RABBITMQ_PASSWORD` - RabbitMQ credentials

## Related Services

- [Manager Service](../manager/README.md) - Device management
- [Monitor Service](../monitor/README.md) - Temperature monitoring
- [Generator Service](../generator/README.md) - Mock data generator
- [Client](../client/README.md) - Web dashboard
