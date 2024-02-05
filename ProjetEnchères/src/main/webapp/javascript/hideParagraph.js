
        
 function fadeOut(elementId) {
        var element = document.getElementById(elementId);
        var opacity = 1;
        var interval = 50; // Interval in milliseconds
        var duration = 3000; // Duration of the fade out effect in milliseconds
        var deltaOpacity = interval / duration;

        var fadeOutInterval = setInterval(function() {
            if (opacity > 0) {
                opacity -= deltaOpacity;
                element.style.opacity = opacity;
            } else {
                clearInterval(fadeOutInterval);
                element.style.display = 'none'; // Hide the element when fade out is complete
            }
        }, interval);
    }

    // Call the fadeOut function with the ID of the element you want to fade out