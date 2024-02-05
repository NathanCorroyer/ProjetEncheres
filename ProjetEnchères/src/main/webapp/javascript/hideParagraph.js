function hideParagraph(paragraphId) {
            // Get the paragraph element by its ID
            var paragraph = document.getElementById(paragraphId);
            
            // Set a timeout to hide the paragraph after 5 seconds (5000 milliseconds)
            setTimeout(function() {
                paragraph.style.display = "none"; // Hide the paragraph
            }, 5000); // 5000 milliseconds = 5 seconds
        }
        
 function fadeOut(elementId) {
        var element = document.getElementById(elementId);
        var opacity = 1;
        var interval = 50; // Interval in milliseconds
        var duration = 1000; // Duration of the fade out effect in milliseconds
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