# 💳 ATM Challenge - Aplicación de Cajero Automático

Este proyecto simula un cajero automático con arquitectura basada en microservicios y una aplicación de escritorio por línea de comandos. Fue desarrollado para cumplir con el challenge técnico **"Producto ATM – Challenge JAVA"**.

## 🧩 Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA + H2
- Docker
- Picocli (CLI profesional)
- Maven

---

## 🎯 Objetivo del proyecto

Simular un cajero automático que permita al usuario:

1. Iniciar sesión con su **número de tarjeta de débito**
2. Consultar el saldo de una cuenta
3. Realizar un depósito a una cuenta (propia o de otro)
4. Realizar una extracción

---

## 🎮 Modos de ejecución disponibles

Este proyecto permite ejecutar el cliente de consola de dos formas diferentes:

### ✅ Modo 1: CLI Profesional (Picocli)

Ideal para uso técnico, integración con scripts o testing automatizado.

- Ejecutar la clase `CliApp` desde IntelliJ o terminal
- Comandos disponibles:

```bash
login --tarjeta 1234567890123456
saldo --tarjeta 1234567890123456 --cuenta 001
depositar --tarjeta 1234567890123456 --cuenta 002 --monto 1000
extraer --tarjeta 1234567890123456 --cuenta 001 --monto 500
```

### ✅ Modo 2: CLI Interactivo (Scanner)

Ideal para simular un flujo de usuario real, con menú paso a paso.

- Ejecutar la clase `ScannerApp`
- Mostrará un menú como este:

```
1. Login
2. Consultar saldo
3. Depositar
4. Extraer
5. Salir
```

> 🧠 **Nota técnica:**  
> La clase `ScannerApp` también interactúa con los mismos microservicios REST (`auth-service`, `account-service`) utilizando el cliente HTTP `AtmRestClient`.  
> Esto garantiza que, aunque la entrada sea vía menú con `Scanner`, la lógica distribuida y la arquitectura basada en microservicios se respetan completamente.

---

## 📦 ¿Cómo correr los microservicios?

1. Clonar el repo y compilar con Maven:
   ```bash
   git clone https://github.com/gelneryus/ATMchallenge.git
   cd ATMchallenge
   mvn clean install
   ```

2. Correr cada microservicio desde su clase `Application`:
   - `auth-service`
   - `account-service`
   - `transaction-service`
   - `gateway-service`

---

## ℹ️ Notas importantes

- En esta implementación, se utiliza el campo `tarjeta` como identificador único del usuario (en vez de DNI), en línea con el enunciado original.
- El sistema responde con mensajes claros como “✔ Ingreso exitoso”, “✅ Retire su dinero”, o errores específicos en cada operación.
- Todos los comandos están disponibles con `--help`, por ejemplo:
  ```bash
  java -jar console-app.jar --help
  ```

---

## 🧪 Datos de prueba disponibles

### 🧾 Tarjetas (auth-service)

| Número de Tarjeta | Titular         | Estado  |
|-------------------|------------------|---------|
| 12345             | Joel Vallejos    | Activa  |
| 67890             | Joel Vallejos    | Activa  |
| 99999             | María Pérez      | Activa  |
| 00000             | Carlos Ramírez   | Inactiva ❌

### 🏦 Cuentas (account-service)

| Nº Cuenta | CBU        | Titular         | Saldo Inicial |
|-----------|------------|------------------|----------------|
| 12345     | CBU12345   | Joel Vallejos    | 100000.00      |
| 67890     | CBU67890   | Joel Vallejos    | 50000.00       |
| 99999     | CBU99999   | María Pérez      | 2500.00        |
| 11111     | CBU11111   | Carlos Ramírez   | 300000.00      |

### 📜 Transacciones precargadas (transaction-service)

| Tipo        | Tarjeta | Cuenta | Importe   | Estado | Mensaje                            |
|-------------|---------|--------|-----------|--------|------------------------------------|
| DEMO        | 12345   | 67890  | 0.00      | ✅     | Registro demo                      |
| DEPÓSITO    | 12345   | 99999  | 2000.00   | ✅     | Depósito a cuenta ajena            |
| EXTRACCIÓN  | 12345   | 12345  | 500.00    | ✅     | Extracción propia                  |
| SALDO       | 67890   | 67890  | 0.00      | ✅     | Consulta de saldo multicuenta      |

> 💡 Podés usar estos datos directamente desde el CLI con `ScannerApp` o `PicocliApp` para probar todas las operaciones del sistema.
