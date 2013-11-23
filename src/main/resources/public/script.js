       $(document).ready(function() {

         reset();

       });

       function fill(){
        $( ".board-field" ).addClass('not-allowed').removeClass('board-field').off('click'); 
        $("#new_game").css('display','block');
      }


       function new_game(){

        var player = 1;
        $("#next_player").html('Spilari X gerir fyrst'); 
        $("#results").removeClass('alert alert-success').html(''); 
        $("#new_game").css('display','none');
        $( ".not-allowed" ).html('&nbsp;').addClass('board-field').removeClass('not-allowed').on('click'); 

        $( ".board-field" ).click(function() {



           var x, y, tPl = player, selectedField = $(this);



           if (selectedField.text() != '×' && selectedField.text() != 'o' ) {
             if(player == 1){
               x = '×';
               y = 'O';
               player = 2;
             }
             else
             {
               x = 'o';
               y = 'X';
               player = 1;
             }

            $("#next_player").html('Spilari ' + y + ' gerir næst');
            
           }

           //$( this ).attr(value, player)
           selectedField.text(x);
           selectedField.addClass('not-allowed').removeClass('board-field').off('click');

           send($( this ).attr('data-value'), tPl); 
         });


          $( "#new_game" ).click(function() {
          new_game();
          });

                $.ajax({
                   type: 'get',
                   url: '/new',
                   data: ''
                 }).done(function(win) {
                 }).fail(function() {
                   $('#results').html('Villa kom upp').attr('class', 'alert alert-danger');
                 });


        }

         function send(i, p){

                 var val = i;

                 $.ajax({
                   type: 'post',
                   url: '/add',
                   data: 'val=' + val
                 }).done(function(win) {
                   if (win != ""){
                   $('#results').html(win).attr('class', 'alert alert-success');
                    score(p);
                    $("#next_player").html('Leik lokið');
                    fill();}
                 }).fail(function() {
                   $('#results').html('Villa kom upp').attr('class', 'alert alert-danger');
                 });

         }

         function score(p) {
            $.ajax({
                   type: 'post',
                   url: '/score',
                   data: 'val=' + p
                 }).done(function(result) { 
                      $( "#score" + p ).html(result);
                  });
               }
          function reset() {
            $.ajax({
              type: 'post',
              url: '/reset',
              data: ''
            }).done(function() {
              new_game();
              $("#score1").html('0');
              $("#score2").html('0');
            });
          }