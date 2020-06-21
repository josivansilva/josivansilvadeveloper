
/**
 * Submits a form by id in 5 seconds.
 * 
 * @param id the element id.
 */
function submitWithTimer (id) {
	console.log ('Starting submitWithTimer.');
	setTimeout(function(){ 
		$("#"+id).submit(); 
	}, 5000);
	console.log ('Finishing submitWithTimer.');
}

/**
 * Hides an element by id in 5 seconds.
 * 
 * @param id the element id.
 */
function hideWithTimer (id) {
	console.log ('Starting hideWithTimer.');
	setTimeout( function(){
		$('#'+id).fadeOut('slow');
	}, 15000);
	console.log ('Finishing hideWithTimer.');
}

/**
 * Scrolls the body to top.
 * 
 */
function scrollToTop() {
	console.log ('Starting scrollToTop.');
	$('body').scrollTo(0);
}

/**
 * Scrolls the body to given position.
 * 
 */
function scrollToPosition(position) {
	$('body').scrollTo(position);
}

/**
 * Decodes an encoded URI.
 * 
 */
function urldecode (str) {
	return decodeURIComponent ((str+'').replace(/\+/g, '%20'));
}

/**
 * Hides the message container according with its container.
 * 
 * @param containerId the container id. 
 */
function hideMessageByContainer (containerId) {
	$("#"+containerId).fadeOut('slow');
}

/**
 * Hides the message container.
 */
function hideMessage () {
	$("#container-message").fadeOut('slow');
}

/**
 * Checks if a given field value is empty or not.
 * 
 * @param value the field value.
 */
function isEmpty (value) {
	var success = false;
	if (value == null || value.length == 0) {
		success = true;
	}
	return success;
}

/**
 * JQuery function that redirects to a specified url.
 * 
 * @param url the url.
 */
function redirectTo (url) {
	$(location).attr ('href', url);
}

/**
 * Checks if a given email is valid or not.
 * 
 * @param url the url.
 */
function isValidEmail (value) {
    var PATTERN =/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
    if (PATTERN.test (value)){         
		return true;
    } else {
    	return false; 
    }
}

/**
 * Gets the url parameters from the URL.
 * 
 * @returns url get parameters
 */
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

/**
 * Sets the url parameters from the URL to empty.
 * 
 */
function removeUrlVars() {
	location.href=location.href.replace(/&?e=([^&]$|[^&]*)/i, "");
}

/**
 * Opens a url in a new tab.
 */
function openInNewTab (url, scrollToTarget) {
	//$('body').scrollTo (scrollToTarget);
	var win = window.open (url, '_blank');
	win.focus();
}

/**
 * Disables a style sheet given its href.
 */
function disableStyleSheet (href) {
	var styleSheets = document.styleSheets;
	for (var i = 0; i < styleSheets.length; i++) {
	    if (styleSheets[i].href.indexOf (href) != -1) {
	        styleSheets[i].disabled = true;
	        console.log ('disabling styleSheet: ' + styleSheets[i].href);
	        break;
	    }
	}
}
