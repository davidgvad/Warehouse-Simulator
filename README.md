# Project3: Logistics Simulation

![GitHub language count](https://img.shields.io/github/languages/count/davidgvad/Warehouse)
![GitHub license](https://img.shields.io/github/license/davidgvad/Warehouse)

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)

---
## Project Structure

```plaintext
Warehouse-Simulator/
|─── Project/
├────── src/
│     ├── Truck.java
│     ├── Shipment.java
│     ├── LoadData.java
│     ├── Time.java
│     ├── Main.java
│     ├── Schedule.java
│     ├── WareHouse.java
│     ├── Node.java
│     ├── Queue.java
│     └── ArrayList.java
├────── data/
│     ├── warehouse.txt
│     ├── shipment.txt
│     ├── truck.txt
|     └── log.txt
├────── tests/
│     ├── TruckTest.java
│     ├── ShipmentTest.java
│     └── WareHouseTest.java
├── .gitignore
├── LICENSE
├── README.md
```
## Project Overview

**Warehouse** is a Java-based simulation tool designed to manage and track the operations of trucks, shipments, and warehouses within a logistics network. The simulation models real-world logistics scenarios, allowing users to observe and analyze the efficiency of shipment assignments, truck movements, and warehouse operations.

### Key Objectives

- **Dynamic Shipment Assignment:** Automatically assigns shipments to trucks based on proximity and capacity constraints.
- **Real-Time Simulation:** Simulates the movement of trucks and the delivery of shipments in real-time.
- **Comprehensive Logging:** Logs all actions and statuses to `log.txt` for detailed tracking and analysis.
- **Random Data Generation:** Generates random data files for warehouses, trucks, and shipments if no input files are provided.

---

## Features

- **Dynamic Shipment Assignment:** Optimizes truck assignments based on shipment proximity and truck capacity.
- **Real-Time Truck Movements:** Simulates real-time movements of trucks between warehouses.
- **Detailed Logging:** Records all significant actions and status changes in a `log.txt` file.
- **Random Data Generation:** Facilitates simulation without pre-existing data by generating random warehouses, trucks, and shipments.
- **Extensible Architecture:** Modular design allows for easy addition of new features or modifications.
- **Robust Error Handling:** Implements comprehensive error handling to ensure simulation stability.

---

## Technology Stack

- **Programming Language:** Java (Version 11)
- **Version Control:** Git
- **Hosting Platform:** GitHub
- **IDE:** IntelliJ IDEA / Eclipse / VSCode 
- **Testing Framework:** JUnit

---

## Installation

### Prerequisites

- **Java Development Kit (JDK):** Ensure you have JDK 11 or higher installed.
  - **Verify Installation:**
    ```bash
    java -version
    ```

- **Git:** Ensure Git is installed on your system.
  - **Verify Installation:**
    ```bash
    git --version
    ```
  - **Download Git:** [Git Downloads](https://git-scm.com/downloads)

### Clone the Repository

1. **Open Git Bash (Windows) or Terminal (macOS/Linux).**

2. **Navigate to the desired directory where you want to clone the project:**
   ```bash
   cd path/to/your/directory
   ```
3. **Git clone the repo:**
    ```bash
    git clone https://github.com/davidgvad/Warehouse-Simulator.git
    ```
4. **Navigate into the Project Directory:**
    ```bash
    cd Project
    ```
## Usage

### Run Simulation
1. **Compile the Java Source Files:**
    ```bash
    javac src/*.java
    ```
2. **Run the Simulation:**
    ```bash
    java -cp src Main data/truck.txt data/shipment.txt data/warehouse.txt
    ```  
   

