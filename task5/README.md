# DSE Programming Task 5

## First use case

Here I used the **Fire and Forget Pattern** because the requirement is only to send a log Message so I **don't need** an  
**acknowledgment** to know if the request was received or handled  
or to wait for a **result** from the server and to check for the result.
For The Communication I used UDP because it is faster than TCP and I don't need to be sure that the message arrives at the Server.

## Second use case
Here I used the **Sync with Server** Pattern because when I need free storage space  
the Clients needs an **acknowledgment** to know if the Server handled the request so that there is again free storage space to use.  
Also for the Communication I used TCP because over TCP I can be sure that the message arrives at the Server.

## Third use case
Here I used the **Polling Pattern**  because when I want to send a lot of log messages  
The Serve needs to **reserve space** for the messages and the Client needs to **compress** the messages both of this takes Time
with The Polling Pattern I can send the request to reserve Space while the Client compresses the messages.  
When the Client is done with compressing he can asks if the Server is done with reserving space via the Polling Object.  
When also the Server is done with reserving space the Client can send the messages to the Server.
For the Communication for the Polling Pattern I used TCP because over TCP I can be sure that the message arrives at the Server to reserve Space.
For the Communication To add the Logs I used UDP because it is faster than UDP and I don't need to be sure that the message arrives at the Server.

## Fourth use case
Here I used the **Callback Pattern**  because when I want to search for Logs I will print the result immediately to the user when the Serve gives me the response.
For the Communication I used here also TCP to be sure that the message arrives at the Server and the user gets the result.

