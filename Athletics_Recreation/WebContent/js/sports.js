function registerUserSport(sportId, userId) {
    $.ajax({
        type: "POST",
        url: "registerSport",
        data: {
            sportId: sportId,
            userId: userId
        },
        success: function(response) {
            alert(response);
        },
        error: function(error) {
        	alert(error);
        }
    });
}