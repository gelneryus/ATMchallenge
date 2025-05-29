# 💻 Console App - Cajero Automático CLI

Aplicación de consola en Java que simula un cajero automático. Permite realizar operaciones de **login, consulta de saldo, extracción y depósito**, interactuando con microservicios vía REST.

Incluye dos formas de uso:
- ✅ **CLI moderno por comandos y flags** (estilo Unix, con `--dni`, `--cuenta`, etc.)
- 🧾 **Modo interactivo clásico** (menú en consola si no se pasan argumentos)

---

## 🚀 Ejecución

### 1. Compilar
```bash
mvn clean package
```

---

## ✅ Modo CLI (recomendado)

Usando comandos explícitos:

```bash
# Iniciar sesión
java -jar target/console-app.jar login --dni 12345678

# Consultar saldo
java -jar target/console-app.jar saldo --dni 12345678 --cuenta 1001

# Depositar dinero
java -jar target/console-app.jar depositar --dni 12345678 --cuenta 1001 --monto 500

# Extraer dinero
java -jar target/console-app.jar extraer --dni 12345678 --cuenta 1001 --monto 200
```

### ℹ️ Ayuda disponible
```bash
java -jar target/console-app.jar --help
```

---

## 🧾 Modo interactivo (fallback por default)

Si ejecutás sin argumentos:

```bash
java -jar target/console-app.jar
```

Se activa el menú tipo terminal clásico (vía `ConsoleRunner`), con opciones paso a paso.

---

## ⚙️ Dependencias principales

- `picocli` para parseo de argumentos y subcomandos
- `Spring Boot` para modo interactivo
- `RestTemplate` (vía `AtmRestClient`) para llamadas a microservicios
- `H2` como base de datos en memoria para persistencia ligera

---

## 📦 Estructura del proyecto

```
console-app/
├── CliApp.java        ← Entrada CLI con picocli
├── ConsoleApp.java    ← Entrada general que decide modo CLI vs menú
├── ConsoleRunner.java ← Modo menú interactivo
├── client/            ← Clientes REST: login, saldo, extracciones
├── command/           ← Comandos usados en modo menú
├── dto/               ← DTOs simples
└── resources/
    └── application.yml
```

---

## 🧠 Decisiones de diseño y arquitectura

- **Arquitectura basada en microservicios**, con separación clara de responsabilidades:
  - `auth-service`: validación de tarjetas.
  - `account-service`: lógica de cuentas (saldo, extracción, depósito).
  - `transaction-service`: registro de auditoría.
  - `gateway-service`: centralización de rutas.
  - `console-app`: interfaz de consola tipo cliente.

- **DTOs y modelos compartidos** mediante un módulo `atm-commons`.

- Uso de **Spring Boot** para rápida configuración y facilidad de testing.

- **Persistencia en H2** para mantener la simpleza del entorno local y cumplir con el requisito de base en memoria.

---

## ⚖️ Decisiones técnicas y compensaciones

- No se incluyó seguridad (ej: JWT, OAuth2) para simplificar la entrega en tiempo y enfocarse en la funcionalidad base.

- No se implementaron pruebas unitarias(algunas) o integración por falta de tiempo, aunque la estructura lo permite fácilmente.

- No se aplicaron contenedores (Docker) ni pipelines CI/CD, pero la arquitectura es compatible con una futura integración.

- No se usaron patrones avanzados como CQRS/Event Sourcing, ya que el alcance no lo justificaba.

---

## ⏱ Tiempo estimado de desarrollo

- Análisis inicial + diseño de arquitectura: 1 hora
- Implementación de microservicios: 5 horas
- Desarrollo del CLI con Picocli: 2 horas
- Pruebas manuales + validaciones: 2 horas
- Documentación y ajustes: 1 hora

**Total estimado: 11 horas**

---

## ⚖️ Decisiones técnicas y compensaciones

Por limitaciones de tiempo, se priorizó cumplir con todos los requisitos funcionales de forma sólida y extensible. A continuación se detallan decisiones técnicas tomadas y qué mejoras podrían haberse agregado para robustecer el sistema en un entorno real de producción:

### 1. 🔐 Autenticación y Seguridad (No incluido)
**Qué se hubiese agregado:** Autenticación JWT o mediante OAuth2 con validación de PIN o MFA.

**Por qué:** En un sistema real no se permite operar solo con DNI o tarjeta. Se debería validar identidad, PIN o clave de un solo uso.

**Caso hipotético:**
> Un atacante obtiene el número de tarjeta de un usuario y realiza extracciones sin PIN ni verificación. Con autenticación robusta, eso no sería posible.

**Mejora:** Protección de endpoints críticos, control de acceso por roles, sesión expirada, trazabilidad segura.

---

### 2. 🧪 Pruebas automatizadas (No incluidas)
**Qué se hubiese agregado:** Pruebas unitarias con JUnit, mocks con Mockito y pruebas de integración con TestContainers.

**Por qué:** Asegura que los servicios funcionen correctamente ante cambios futuros.

**Caso hipotético:**
> Se cambia la lógica de extracción para permitir overdraft temporal, pero no se actualiza bien la validación. Sin pruebas, el error pasa a producción.

**Mejora:** Confiabilidad, menor tiempo de debugging, mayor estabilidad en despliegues.

---

### 3. 🐳 Dockerización (No incluido)
**Qué se hubiese agregado:** Dockerfile por cada microservicio y `docker-compose.yml` para levantar todo el sistema con un solo comando.

**Por qué:** Facilita despliegue, testing local y portabilidad del entorno.

**Caso hipotético:**
> Un evaluador quiere probar tu proyecto y no tiene Java ni Maven instalado. Con Docker, sólo necesita `docker compose up`.

**Mejora:** Portabilidad, consistencia de entorno, onboarding más rápido para nuevos devs.

---

### 4. 📊 Observabilidad y Logs (No implementado)
**Qué se hubiese agregado:** Logs estructurados con SLF4J + integración con ELK/Grafana. Además de métricas vía Micrometer/Prometheus.

**Por qué:** En producción, necesitás saber qué pasó, cuándo y por qué.

**Caso hipotético:**
> Un usuario reporta que su saldo bajó sin motivo. Sin logs no podés auditar ni reconstruir la operación.

**Mejora:** Auditoría, resolución de incidentes, monitoreo de salud del sistema.

---

### 5. ♻️ Manejo de errores global (No incluido)
**Qué se hubiese agregado:** Manejo centralizado de excepciones con `@ControllerAdvice` en Spring.

**Por qué:** Brinda respuestas consistentes y claras en vez de stacktraces confusos.

**Caso hipotético:**
> Una cuenta no existe y se devuelve un 500 en vez de un 404 con mensaje "Cuenta inexistente".

**Mejora:** Mejor DX (developer experience), menos bugs visibles, errores legibles.

---

### 6. 🏗️ CI/CD automatizado (No incluido)
**Qué se hubiese agregado:** GitHub Actions para test + build + deploy automático.

**Por qué:** Asegura calidad de código y acelera entregas.

**Caso hipotético:**
> Subís un cambio que rompe login. Con CI, se detecta antes del deploy.

**Mejora:** Despliegues seguros, control de calidad automático.

## ✍️ Autor

Joel Vallejos – 2025  

---
![image](https://github.com/user-attachments/assets/e64097a9-8f11-4c06-9d6c-cc73e3c0b0b6)

## 🔗 Repositorio público

https://github.com/tu-usuario/challenge-atm
