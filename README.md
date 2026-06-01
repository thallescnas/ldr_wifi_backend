# Arduino Backend - LDR WiFi

Este servidor é o ponto central de recepção e processamento de dados de luminosidade coletados por sensores LDR e transmitidos via WiFi por dispositivos Arduino/ESP8266.

## Funcionalidades

- **Ingestão de Dados**: API REST para receber leituras de luminosidade em tempo real.
- **Monitoramento**: Consulta rápida do último estado registrado pelo sensor.
- **Histórico em Memória**: Armazenamento volátil das leituras para análise durante a sessão.

## Tecnologias Utilizadas

- **Linguagem**: Java 21
- **Framework**: Spring Boot 4.0.6 (Spring WebMVC)
- **Build Tool**: Maven
- **Infraestrutura**: Docker & Docker Compose

## Estrutura do Projeto

```text
src/main/java/me/natu/arduinobackend/
├── controller/     # Endpoints da API (Sempre expõe a porta 8080 por padrão)
├── dto/            # Objetos de transferência de dados (Payloads JSON)
├── service/        # Lógica de negócio e armazenamento das leituras
└── ArduinoBackendApplication.java # Classe principal de inicialização
```

## Referência da API

A API opera sob o prefixo `/api/arduino`.

### 1. Registrar Leitura
Envia um novo valor de luminosidade capturado pelo sensor.

- **URL:** `/api/arduino/`
- **Método:** `POST`
- **Payload:**
  ```json
  {
    "value": 542
  }
  ```
- **Exemplo de teste (cURL):**
  ```bash
  curl -X POST http://localhost:8080/api/arduino/ \
       -H "Content-Type: application/json" \
       -d '{"value": 542}'
  ```

### 2. Consultar Última Leitura
Recupera o valor mais recente enviado pelo dispositivo.

- **URL:** `/api/arduino/`
- **Método:** `GET`
- **Exemplo de teste (cURL):**
  ```bash
  curl http://localhost:8080/api/arduino/
  ```

## Instalação e Execução

### Execução Local
Se você possui o JDK 21 instalado, pode rodar o projeto via wrapper do Maven:

```bash
./mvnw spring-boot:run
```
O servidor estará disponível em: `http://localhost:8080`

### Execução com Docker
Para subir o ambiente isolado com Docker Compose:

```bash
docker compose up -d
```

## Integração com Firmware

Este backend é a contraparte do projeto [LDR WiFi](https://github.com/thallescnas/ldr_wifi_firmware). 

**Configuração necessária no Firmware (`Secret.h`):**
- `BACKEND_ADDRESS`: Endereço IP da máquina que hospeda este servidor.
- `BACKEND_PORT`: `8080`
- **Rota de envio**: `/api/arduino/`

Para detalhes sobre o hardware (Sensores LDR, Módulo ESP8266), consulte o repositório do firmware.
