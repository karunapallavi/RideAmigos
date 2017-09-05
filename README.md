# RideAmigos
Updating user location to a given URL


This application requests the user's location as latitude+longitude and sends it to the URL that can be hardcoded into the application. 
It uses the NETWORK_PROVIDER to request the location.
  as an alternative, we could use GPS_PROVIDER to request for the location as well and compare the accuracies of both and then use the one with the best accuracy to be sent to the URL.

A countdown timer is used so that every 15s, the updated location is sent
This timer starts when the START button is clicked
When the STOP button is clicked, the timer stops and the location is sent one last time
  as an alternative, the START and the STOP button can be one and the same - as in the timer would stop on the click of the START button.
  
This application updates the location every 15s to the given URL - this action is performed using an HTTP POST method
  this can also be done by sending the latitude and Longitude as a JSON 
