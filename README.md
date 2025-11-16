Event Management System in Java

A desktop-based event planning and management system built in Java, following a three-tier architecture and developed using the principles of the simplified Larman methodology.
The app allows organizers to create events, manage guests, performers, locations, and attendance confirmations, all through a structured client-server model.

Features
Event Management

Create, update and delete events

Assign performers with performance durations

Add guests to events

Track RSVP confirmations

Select event location, date, time, and type

Guest Management

Add new guests

Edit existing guests

Delete guests

Search guests by criteria (e.g. last name)
Performer Management

Create performers

Update performer info

Delete a performer

Location Management

Add new locations

Update existing ones

Remove locations

Capacity validation

Authentication

Login with username and password

Role: system user (organizer)

Validation + feedback messages

System Architecture

Client-side UI (Swing)

Server-side logic (Java socket server)

Database logic (custom broker layer)

REST-like request/response model over sockets

Full CRUD for all domain objects

![Login Screen](images/picture9.png)
![Main Screen](images/picture10.png)
![Create Event](images/picture2.png)
![Manage Event](images/picture3.png)

System Architecture
Three-Layer Architecture

UI Layer: Swing forms (login, event creation, guest management, admin views)

Application Logic: Controller + System operations (SOPrijaviKorisnika, SOKreirajDogadjaj, etc.)

Data Layer: Database broker handling SQL operations

Core Domain Model

The system is built around these entities:

User

Event

Guest

Performer

Location

Engagement (link between Event + Performer)

Confirmation (guest attendance)

Full conceptual model is documented in the original project specification.
