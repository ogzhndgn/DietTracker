/**
 * Created by the Poet on 22.10.2015.
 */
$(document).ready(function () {
    var foodSuggestion = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: 'getfood?prefix=%QUERY',
            wildcard: '%QUERY'
        }
    });

    //var movies = new Bloodhound({
    //    remote: {
    //        url: 'http://api.themoviedb.org/3/search/movie?query=%QUERY&api_key=f22e6ce68f5e5002e71c20bcba477e7d',
    //        filter: function (movies) {
    //            // Map the remote source JSON array to a JavaScript object array
    //            return $.map(movies.results, function (movie) {
    //                return {
    //                    value: movie.original_title
    //                };
    //            });
    //        }
    //    }
    //});
    foodSuggestion.initialize();
    $('#foodid').typeahead(null, {
        //int: true,
        //highlight: true,
        //minLength: 1,
        name: 'food-suggestion',
        display: 'value',
        source: foodSuggestion
    });
});