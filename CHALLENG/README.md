# Challenge Java - Producto ATM

## 🛠️ Objetivo
Simulación de un cajero automático usando una app de consola en Java que interactúa con microservicios RESTful. El usuario puede iniciar sesión, consultar saldo, extraer y depositar dinero en cuentas mediante comandos CLI.

---

## ⚙️ Estructura del Proyecto

- `console-app`: Aplicación de consola que expone comandos CLI.
- `auth-service`: Maneja login por número de tarjeta.
- `account-service`: Maneja cuentas, saldos, depósitos y extracciones.
- `transaction-service`: Audita todas las operaciones (trazabilidad).
- `atm-commons`: DTOs y modelos compartidos.
- `gateway-service`: Entrada unificada opcional (configurable).

---

## 🚀 Cómo ejecutar

```bash
# Clonar el proyecto
git clone <URL_DEL_REPO>
cd CHALLENG

# Compilar todos los módulos
mvn clean install

# Ejecutar servicios
cd auth-service && mvn spring-boot:run
cd account-service && mvn spring-boot:run
cd transaction-service && mvn spring-boot:run

# Ejecutar consola
cd console-app
java -jar target/console-app-1.0.0.jar <comando>
```

---

## 🧪 Ejemplos de uso

```bash
java -jar console-app.jar login 12345678
java -jar console-app.jar extraer 12345678 1001 200
java -jar console-app.jar depositar 12345678 3020000010000000000001 500
java -jar console-app.jar saldo 12345678 1001
```

---

## 📡 Ejemplos de respuestas JSON

### Login exitoso
```json
{
  "mensaje": "Ingreso exitoso"
}
```

### Extracción exitosa
```json
{
  "mensaje": "Retire su dinero"
}
```

### Depósito exitoso
```json
{
  "mensaje": "Depósito exitoso"
}
```

### Consulta de saldo
```json
{
  "saldo": 15700.50
}
```

---

## ✅ Diseño y decisiones técnicas

- Arquitectura basada en microservicios para simular entorno real.
- Separación de responsabilidades: autenticación, cuentas, auditoría.
- `DataInitializer` para datos precargados.
- Base en memoria (H2) para pruebas rápidas.
- Comunicación HTTP vía RestTemplate y DTOs.

---

## 🧯 Compensaciones realizadas

- Persistencia real no implementada por limitaciones de tiempo.
- CLI básica, sin validaciones extensas.
- Gateway básico sin configuraciones avanzadas.

---

## ⏱ Tiempo estimado de desarrollo

Aproximadamente **X horas** entre análisis, implementación, testing y documentación.

---
![image](https://github.com/user-attachments/assets/602b2958-a686-4d6b-88e9-36b947e7041d)

## 🔗 Repositorio público

https://github.com/tu-usuario/challenge-atm
