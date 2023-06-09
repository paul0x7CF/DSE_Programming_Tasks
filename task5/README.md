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


Für die verschiedenen Use Cases lassen sich die folgenden Muster mit Begründung anwenden:

Erster Use-Case: Einzelnes Log-Nachricht senden
Pattern: Fire and Forget
Begründung: Da der Client einfach eine Log-Nachricht an den Server senden möchte, ohne auf eine Bestätigung oder Rückmeldung zu warten, eignet sich das Fire and Forget-Muster gut. Der Client kann die Nachricht senden und seinen Ablauf fortsetzen, ohne auf eine Reaktion des Servers zu warten.

Zweiter Use-Case: Entfernen alter Log-Einträge
Pattern: Sync with Server
Begründung: Der Client muss in der Lage sein, dem Server anzuweisen, alte Log-Einträge zu entfernen. Hierbei ist eine Synchronisation mit dem Server erforderlich, da der Client sicherstellen muss, dass die alten Einträge erfolgreich entfernt wurden, bevor er weitere Aktionen ausführt. Der Client wartet auf die Antwort des Servers, um fortzufahren.

Dritter Use-Case: Senden von Log-Nachrichten im Bulk mit Kompression und Reservierung von Platz
Pattern: Result Callback
Begründung: Da das Senden von Log-Nachrichten im Bulk Zeit in Anspruch nehmen kann, ist es effizienter, asynchrone Operationen zu verwenden, um die Gesamtabwicklungszeit zu reduzieren. Der Client kann die Kompression der Daten asynchron durchführen und eine Rückruffunktion angeben, die aufgerufen wird, wenn die Kompression abgeschlossen ist. Gleichzeitig kann der Server asynchron zusätzlichen Platz reservieren und eine Rückruffunktion aufrufen, wenn dies abgeschlossen ist. Das Result Callback-Muster ermöglicht parallele Verarbeitung und verbessert die Gesamtperformance.

Vierter Use-Case: Suche nach spezifischen Log-Einträgen basierend auf einem Suchbegriff
Pattern: Polling oder Result Callback (abhängig von der Implementierung)
Begründung: Bei der Suche nach spezifischen Log-Einträgen in Echtzeit kann entweder das Polling-Muster verwendet werden, bei dem der Client in regelmäßigen Abständen Anfragen an den Server stellt, um nach neuen Suchergebnissen zu suchen, oder das Result Callback-Muster, bei dem der Server die Suchergebnisse asynchron an den Client zurückgibt. Die Wahl des Musters hängt von der genauen Implementierung und den Anforderungen der Anwendung ab.