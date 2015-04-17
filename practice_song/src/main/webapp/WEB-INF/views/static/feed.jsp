<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Foundation | Welcome</title>
    <link rel="stylesheet" href="../resources/css/foundation.css" />
    <script src="../resources/js/vendor/modernizr.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
  </head>
  <body>
    
      <div class="row">
        <div class="large-12 columns">
          <div class="panel">
            <h1>Feed Template</h1>
          </div>
        </div>
      </div>
       
      <div class="row">
      
         
        <div class="large-3 columns ">
          <div class="panel">
            <a href="#"><img src="http://placehold.it/300x240&text=[img]"/></a>
            <h5><a href="#">Your Name</a></h5>
              <div class="section-container vertical-nav" data-section data-options="deep_linking: false; one_up: true">
              <section class="section">
                <h5 class="title"><a href="#">Section 1</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 2</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 3</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 4</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 5</a></h5>
              </section>
              <section class="section">
                <h5 class="title"><a href="#">Section 6</a></h5>
              </section>
            </div>
     
          </div>
        </div>
        
         
         
        <div class="large-6 columns">
     
           
          <div class="row">
            <div class="large-2 columns small-3"><img src="http://placehold.it/80x80&text=[img]"/></div>
            <div class="large-10 columns">
              <p><strong>Some Person said:</strong> ${writes.get(0).WRITES_CONTENT}</p>
              <ul class="inline-list">
                <li><a href="">Reply</a></li>
                <li><a href="">Share</a></li>
              </ul>
     
     
              <h6>2 Comments</h6>
              <div class="row">
                <div class="large-2 columns small-3"><img src="http://placehold.it/50x50&text=[img]"/></div>
                <div class="large-10 columns"><p>${comments.get(0).COMMENTS_CONTENT}</p></div>
              </div>
              <div class="row">
                <div class="large-2 columns small-3"><img src="http://placehold.it/50x50&text=[img]"/></div>
                <div class="large-10 columns"><p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit</p></div>
              </div>
            </div>
          </div>
           
     
          <hr/>
     
           
          <div class="row">
            <div class="large-2 columns small-3"><img src="http://placehold.it/80x80&text=[img]"/></div>
            <div class="large-10 columns">
              <p><strong>Some Person said:</strong> Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit, dolore aliqua non est magna in labore pig pork biltong.</p>
              <ul class="inline-list">
                <li><a href="">Reply</a></li>
                <li><a href="">Share</a></li>
              </ul>
            </div>
          </div>
           
     
          <hr/>
     
           
          <div class="row">
            <div class="large-2 columns small-3"><img src="http://placehold.it/80x80&text=[img]"/></div>
            <div class="large-10 columns">
              <p><strong>Some Person said:</strong> Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit, dolore aliqua non est magna in labore pig pork biltong.</p>
              <ul class="inline-list">
                <li><a href="">Reply</a></li>
                <li><a href="">Share</a></li>
              </ul>
     
     
              <h6>2 Comments</h6>
              <div class="row">
                <div class="large-2 columns small-3"><img src="http://placehold.it/50x50&text=[img]"/></div>
                <div class="large-10 columns"><p>Bacon ipsum dolor sit amet nulla ham qui sint exercitation eiusmod commodo, chuck duis velit. Aute in reprehenderit</p></div>
              </div>
            </div>
          </div>
           
     
        </div>
     
         
         
        <aside class="large-3 columns hide-for-small">
          <p><img src="http://placehold.it/300x440&text=[ad]"/></p>
          <p><img src="http://placehold.it/300x440&text=[ad]"/></p>
        </aside>
     
      </div>
    
    <script src="../resources/js/vendor/jquery.js"></script>
    <script src="../resources/js/foundation.min.js"></script>
    <script>
      $(document).foundation();
    </script>
  </body>
</html>
