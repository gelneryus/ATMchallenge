# 💸 Proyecto ATM – Cajero Automático en Java

Este proyecto simula un **sistema real de cajero automático**, implementado como **una arquitectura de microservicios + cliente CLI**. Permite realizar operaciones como login, consulta de saldo, depósito y extracción, **desde línea de comandos**, consumiendo microservicios REST construidos con Spring Boot.

Se implementaron buenas prácticas modernas en arquitectura, DTOs compartidos, capa de servicios desacoplada, estructura de carpetas limpia y uso de librerías profesionales como Picocli y SLF4J.

> ✅ Desarrollado en 11 horas como parte de un challenge técnico, con foco en extensibilidad, claridad y robustez.

---

## 🏗️ Arquitectura general

```
console-app ← REST → gateway-service → [auth, account, transaction]
           ↑
    CLI moderno (Picocli)
```

* `auth-service`: valida tarjetas.
* `account-service`: gestiona saldos, depósitos y extracciones.
* `transaction-service`: registra auditorías.
* `gateway-service`: unifica accesos.
* `atm-commons`: modelos y DTOs reutilizables.
* `console-app`: cliente de consola que simula el uso de un cajero real.

---

## 🚀 Ejecución

### Requisitos

* JDK 11+
* Maven 3.8+
* (Opcional) Docker si se implementa containerización más adelante

### Compilar

```bash
mvn clean package
```


## 🧪 ¿Cómo ejecutar la aplicación por consola?

### ✅ Opción 1: Desde IntelliJ

1. Ir al módulo `console-app`
2. Abrir la clase `CliApp` y correr el `main()` una vez
3. Ir a `Run > Edit Configurations...`
4. En el campo **Program arguments**, escribir alguno de los siguientes comandos:

```bash
# Login
login --tarjeta 1234567890123456

# Consultar saldo
saldo --tarjeta 1234567890123456 --cuenta 001

# Depositar
depositar --tarjeta 1234567890123456 --cuenta 002 --monto 1000

# Extraer
extraer --tarjeta 1234567890123456 --cuenta 001 --monto 500




### Ejecutar CLI

```bash
# Login
java -jar console-app/target/console-app.jar login --dni 12345678

# Consultar saldo
java -jar console-app/target/console-app.jar saldo --dni 12345678 --cuenta 1001

# Depósito
java -jar console-app/target/console-app.jar depositar --dni 12345678 --cuenta 1001 --monto 500

# Extracción
java -jar console-app/target/console-app.jar extraer --dni 12345678 --cuenta 1001 --monto 200
```

---

## 📦 Tecnologías utilizadas

| Módulo         | Tecnología principal     | Propósito                        |
| -------------- | ------------------------ | -------------------------------- |
| CLI (console)  | `picocli`, `Spring Boot` | Cliente con comandos             |
| Microservicios | `Spring Boot`, `H2`      | REST APIs y DB en memoria        |
| Commons        | Java puro                | DTOs compartidos entre servicios |
| Comunicación   | `Feign` / `RestTemplate` | Llamadas HTTP                    |
| Logs           | `SLF4J`                  | Logging estructurado             |

---

## ⚙️ Decisiones técnicas tomadas

### ✔️ Se implementó

* Separación en microservicios reales (no simulados).
* DTOs y modelos unificados en `atm-commons`.
* CLI profesional por comandos y flags.
* Logging estructurado con SLF4J.
* Modo fallback interactivo si no se pasan argumentos CLI.
* Validación de entrada básica.
* Persistencia temporal en memoria con H2.
* Separación por capas (controller, service, model, dto).
* Código desacoplado, listo para testear y escalar.

---

## ⏱️ Tiempo estimado de desarrollo

| Tarea                         | Tiempo estimado |
| ----------------------------- | --------------- |
| Análisis y diseño             | 1 h             |
| Implementación microservicios | 5 h             |
| Desarrollo CLI (Picocli)      | 2 h             |
| Pruebas manuales              | 2 h             |
| Documentación final           | 1 h             |
| **Total**                     | **11 horas**    |

---

## ❌ Mejoras pendientes (por falta de tiempo)

### 🔐 1. Autenticación y autorización

**Qué faltó:** Seguridad robusta (JWT, OAuth2, MFA)

**Por qué es importante:** En producción, no se puede operar sólo con DNI o número de tarjeta. Debe haber validación real de identidad (PIN, clave, factor).

**Beneficio:** Prevención de fraudes, control de sesiones, seguridad a nivel endpoint.

### 🧪 2. Pruebas automatizadas (Unit + Integration)

**Qué faltó:** Test unitarios con `JUnit + Mockito`, test de integración con `TestContainers`.

**Beneficio:** Reduce riesgo de regresiones, mejora confianza al modificar lógica.

**Estrategia futura:** Mockear servicios externos, validar flujo end-to-end con `@SpringBootTest`.

### 🐳 3. Dockerización

**Qué faltó:** Dockerfiles por microservicio y `docker-compose.yml`.

**Beneficio:** Facilita testing, despliegue y onboarding. Aísla el entorno.


### ♻️ 4. Manejo global de errores

**Qué faltó:** `@ControllerAdvice` para respuestas limpias tipo `404`, `400`, `403`.

**Beneficio:** Mejora la experiencia del dev y del cliente, evita respuestas crudas o errores 500 innecesarios.

### 🛠️ 5. CI/CD

**Qué faltó:** Pipelines con GitHub Actions o GitLab CI

**Beneficio:** Validaciones automáticas antes de cada deploy, menos bugs en producción.


### 🔄 6. Encriptación y cumplimiento

**Qué faltó:** Encriptar CBU, tarjetas y logs sensibles con AES o TLS.

**Beneficio:** Cumple con normativas (ej: PCI-DSS), previene filtraciones.

---

## 🧠 Lecciones y decisiones clave

* El foco fue cumplir todos los requerimientos funcionales con una arquitectura real y profesional.
* Se priorizó claridad, extensibilidad y separación de responsabilidades por sobre la cobertura exhaustiva de features.
* El proyecto está **listo para ser ampliado con seguridad, docker, test, y resiliencia sin grandes refactors.**

---

## ✍️ Autor

Joel Vallejos – 2025
📧 [joelgvallejos982001@gmail.com](mailto:joelgvallejos982001@gmail.com)

---

## 🔗 Repositorio

👉 [https://github.com/tu-usuario/challenge-atm](https://github.com/tu-usuario/challenge-atm)

 
# 🧾 Proyecto ATM - Trazabilidad con Trace ID

Este proyecto fue modificado para implementar **traceabilidad completa de logs** usando un `traceId` único por request. Esto permite seguir el flujo de una operación desde la consola hasta cada microservicio.

---

## ✅ ¿Qué incluye?

| Componente         | Mejora aplicada                                       |
|--------------------|--------------------------------------------------------|
| `console-app`      | Interceptor que genera `X-Trace-Id` y lo loguea       |
| `gateway-service`  | Filtro global que propaga/genera `X-Trace-Id`         |
| `transaction-service` | Filtro que captura el `traceId` y lo guarda en MDC |
| Todos los servicios| Logs JSON con `logback-spring.xml` y `MDC` habilitado |

---

## 🚀 ¿Cómo ejecutarlo?

1. **Levantar los microservicios**:
```bash
cd auth-service && mvn spring-boot:run
cd account-service && mvn spring-boot:run
cd transaction-service && mvn spring-boot:run
cd gateway-service && mvn spring-boot:run
```

2. **Ejecutar el cliente por consola**:
```bash
cd console-app
mvn spring-boot:run -Dspring-boot.run.arguments="--login --dni=1234 --pin=5678"
```

---

## 🔍 Ver los logs con traceId

Ejemplo de log JSON:
```json
{
  "@timestamp": "2025-05-29T00:55:21.324+0000",
  "@version": "1",
  "message": "Solicitud de extracción ejecutada",
  "logger_name": "com.atm.transaction.service.TransaccionService",
  "level": "INFO",
  "traceId": "1d3e25c5-4b2a-4e5d-bf6f-764c74a8e398"
}
```

Podés seguir este `traceId` en los logs de todos los servicios para rastrear cualquier operación de punta a punta.

---

## 🛠️ Librerías agregadas

- `logstash-logback-encoder`: logs JSON con soporte para MDC.
- `RestTemplate` con `ClientHttpRequestInterceptor` en `console-app`.

---

## 📁 Estructura modificada

- `logback-spring.xml` → agregado en todos los módulos.
- `TraceIdInterceptor`, `TraceIdFilter`, `TraceIdLoggingFilter` → nuevas clases para trazabilidad.

---




---
![image](https://github.com/user-attachments/assets/e64097a9-8f11-4c06-9d6c-cc73e3c0b0b6)

## 🔗 Repositorio público

https://github.com/tu-usuario/challenge-atm
