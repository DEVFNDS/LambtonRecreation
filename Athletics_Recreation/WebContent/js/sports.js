function registerUserSport(sportId, userId) {
    $.ajax({
        type: "POST",
        url: "registerSport",
        data: {
            sportId: sportId,
            userId: userId
        },
        success: function(response) {
            console.log(response);
        },
        error: function(error) {
            console.error(error);
        }
    });
}