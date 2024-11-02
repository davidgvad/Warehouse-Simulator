# WareHouse: Logistics Simulation

![License](https://img.shields.io/github/license/davidgvad/Warehouse-Simulator)
![Java Version](https://img.shields.io/badge/java-11%2B-blue)

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Design and Architecture](#design-and-architecture)
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

**Warehouse** is a Java-based simulation tool designed to manage and track the operations of trucks, shipments, and warehouses within a logistics network. The simulation models real-world logistics scenarios, allowing users to observe and analyze the efficiency of shipment assignments, truck movements, and warehouse operations. All the data structures used were implemented manually by the author.

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

## Design and Architecture

The **Warehouse-Simulator** project is architected using a modular and object-oriented approach, ensuring scalability and maintainability. At the core is the `Main.java` class, which serves as the entry point of the application, orchestrating the simulation by initializing components and managing the simulation loop. The `LoadData.java` class is responsible for reading and parsing input data from the `warehouse.txt`, `shipment.txt`, and `truck.txt` files, effectively populating the system with initial data for warehouses, shipments, and trucks. The `WareHouse.java` class models individual warehouses, managing their properties such as location coordinates and dock availability, while the `Truck.java` class encapsulates the behavior and attributes of each truck, including capacity, speed, and current assignments. The `Shipment.java` class represents shipments, detailing their source, destination, and size, facilitating the dynamic assignment of shipments to appropriate trucks based on proximity and capacity constraints. To manage time progression within the simulation, the `Time.java` class handles the simulation clock, advancing it in discrete increments and triggering corresponding actions for trucks. The `Schedule.java` interface defines the contract for scheduling actions and logging, which is implemented by relevant classes to ensure consistent logging of activities to `log.txt`. Supporting data structures such as `Queue.java` and `ArrayList.java` are custom implementations that manage collections of trucks and shipments efficiently. The `Node.java` class is utilized within these data structures to represent individual elements, ensuring optimal performance during enqueue and dequeue operations. Together, these components interact seamlessly to simulate real-world logistics operations, dynamically assigning shipments, managing truck movements, and maintaining comprehensive logs, thereby providing a robust and extensible simulation environment.

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
   

