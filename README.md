# TigerCard

## Implemented the solution in two ways:
### 1.) Java classes (target: design a software)
### 2.) Apache spark: implemented in Scala (target: this problem can also be solved by big data tools like spark or pandas etc.)

### Problem Statement

The transport officials from the city of Nepu want to design a payment system for public metro transport. They have come up with the idea of a prepaid card - TigerCard - which is an NFC enabled card that is to be tapped at entry and exit points of the metro stations.

To make the fares easier to understand for the commuters, the city has been divided into zones. Zone 1 is the central area and Zone 2 forms a concentric ring around Zone 1. Each metro station has been assigned to a zone. In the future, more zones will be added as the metro expands.

The problem statement is to design the fare calculation engine for TigerCard. Below are the definitions and fare rules.

### Rules
#### Time of travel
The fare varies based on the time of the travel. There are two types of fares based on time of travel:
*   Peak hours timings

    *   Monday - Friday
        *   07:00 - 10:30, 17:00 - 20:00
    *   Saturday - Sunday
        *   09:00 - 11:00, 18:00 - 22:00

*   Off-peak hours timings
    *   All hours except the above peak hours
    *   If travelling from any station outside Zone 1 to a station in Zone 1 on below times
        *   Monday - Friday 
            *   17:00  - 20:00
        *   Saturday - Sunday
            *   18:00 - 22:00
            
#### Fare
The below table shows the fare that commuters will have to pay for a single journey from station A to station B. There is no concept of a round-trip journey.

    Zones               Peak hours      Off-peak hours
    1 - 1               30              25
    1 - 2 or 2 - 1      35              30
    2 - 2               25              20

#### Fare Capping
Fare capping works by rewarding commuters with free rides after they meet the fare equivalent of a daily, weekly, or monthly pass. With fare capping, social equity is achieved by removing upfront cost barriers associated with the recurrent passes. For example, a single ride costs 30 and the daily pass costs 90, the commuter earns a daily pass after the first 3 rides. For the rest of the day, all rides will be free for the commuter. Due to fare capping, the commuter does not have to invest 90 on the daily pass as they get the same features using a regular card. 

The following capping categories are available:
*   Daily
*   Weekly

#### Capping Limits:

    Zones               Daily Cap       Weekly Cap (Monday - Sunday)
    1 - 1               100             500
    1 - 2 or 2 - 1      120             600
    2 - 2               80              400
    
## Problem to be solved
Given a list of journeys, the program should return the fare applicable. 

**Input Format:** List of journeys for a single commuter, fields include date-time, from-zone, to-zone

**Output:** Computed Fare for the input journeys data

Below are some sample scenarios to consider and understand the problem further. The explanation column describes the applicable rule.

#### Examples
**1. Daily cap reached**

**Input:**

    Day     Time    From Zone   To Zone     Calculated Fare     Explanation
    Monday  10:20   2           1           35                  Peak hours Single fare
    Monday  10:45   1           1           25                  Off-peak single fare
    Monday  16:15   1           1           25                  Off-peak single fare
    Monday  18:15   1           1           30                  Peak hours Single fare  
    Monday  19:00   1           2           5                   The Daily cap reached 120 for zone 1 - 2. Charged 5 instead of 35
**Output:** 120


**2. Weekly cap reached**

**Important Note:** For easier explanation, the daily journeys have been rolled-up to just show the total fare applicable for a day as this makes it easier to understand how the weekly fare capping will be applicable. In the actual implementation, the input will be a list of individual journeys as shown in the previous scenario.

**Input:**

    Day                 Zones       Calculated Daily Fare       Explanation
    Monday              1 - 2       120                         Daily cap reached
    Tuesday             1 - 2       120                         Daily cap reached
    Wednesday           1 - 2       120                         Daily cap reached
    Thursday            1 - 2       120                         Daily cap reached
    Friday              1 - 2       120                         Daily cap reached
    Saturday            1 - 2       0                           A weekly cap of 600 reached
    Sunday              1 - 2       0                           A weekly cap of 600 reached
    Monday (Next week)  1 - 2       100                         New week started

**Output:** 700


 





 
