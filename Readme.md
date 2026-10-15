# Management System

## Project Overview

The Queue Management System is a prototype developed as part of my bachelor's engineering thesis. Its primary goal was to design and implement a system for managing queues of people waiting to be served at customer service facilities.

The project aimed to investigate whether modern web technologies, particularly React, could be effectively used to build a queue management system consisting of multiple independent applications communicating with a central server.

The system enables facility configuration, queue management, ticket distribution, customer service operations, and visual and voice-based ticket announcements.

The project also served as a practical opportunity to explore a modular application architecture and asynchronous communication between distributed system components.

## System Architecture

The system consists of two main components: Core and Facility.

### 1. Core Application

The Core is the central server responsible for system configuration and ticket distribution.

Its main responsibilities include:

Managing facilities and their configurations.

Configuring kiosks, kiosk buttons, and queues.

Receiving requests to generate queue tickets.

Distributing tickets to the appropriate facilities.

Handling asynchronous communication with individual facilities.

Communication between the Core and individual facilities is implemented using RabbitMQ, which acts as a message broker.

RabbitMQ enables asynchronous message delivery through separate communication channels for individual facilities. It also provides message persistence and delivery mechanisms that help the system handle temporary unavailability of receiving components.

### 2. Facility Applications

The Facility component consists of several smaller applications, each responsible for a specific function within the queue management system.

#### Facility Server

The Facility Server handles communication with the Core and provides backend functionality for a particular facility.

Its responsibilities include:

Receiving queue tickets through RabbitMQ.

Storing user accounts and configuration data required by the client application.

Retrieving queue configuration from the Core.

Forwarding called ticket numbers to the announcement system.

#### Client Application

The Client Application is designed for use at customer service desks.

It allows staff to manage queues and handle individual customer visits. Available operations include:

Calling the next ticket.

Postponing a ticket.

Completing a customer visit.

#### Voice Announcement Application

The Voice Announcement Application provides visual and voice-based ticket announcements.

Its main features include:

Announcing ticket numbers using speech synthesis.

Displaying the seven most recently called tickets.

Showing the service desk associated with each announced ticket.

#### Kiosk Application

The Kiosk Application allows customers to obtain a ticket for a selected queue or group of service desks.

After selecting the appropriate service, the kiosk generates a unique ticket number and prints it for the customer. When the ticket is called, the customer is directed to the relevant service desk.

#### Technologies

The project explores the use of modern web technologies and backend infrastructure to implement a distributed queue management system.

- Java

- Spring Boot

- React

- TypeScript

- HTML5 and CSS

- RabbitMQ

- PostgreSQL

- Hibernate / JPA

- WebSocket

- Vite

#### Project Scope and Limitations

The main objective of this engineering project was to verify the feasibility of building a queue management system using web technologies, particularly React, and to evaluate the proposed architecture through a working prototype.

As an academic prototype, the project focuses primarily on core functionality, modular architecture, and communication between system components.

Some aspects that would be necessary in a production-ready system, including comprehensive security mechanisms, may be incomplete or omitted. The project should therefore be considered an engineering prototype rather than a fully production-hardened solution.

Further development could include strengthening security, expanding monitoring capabilities, improving fault tolerance, and extending the system's configuration and reporting features.

#### Purpose of the Project

This project demonstrates the practical application of backend development, frontend development, asynchronous messaging, and modular system design in a real-world-inspired scenario.

It was developed to assess the suitability of web technologies for building a distributed queue management solution and to identify potential areas for future improvement.