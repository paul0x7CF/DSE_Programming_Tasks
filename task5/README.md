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