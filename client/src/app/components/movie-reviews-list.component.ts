import { Component, OnInit } from '@angular/core';
import { Review } from '../models/Review';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { MoviesService } from '../services/movies.service';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit {

  movies!: Review[]
  param$!: Subscription
  query!: string
  noResult = false

  constructor(private router: Router, private activatedRoute: ActivatedRoute,
    private moviesSvc: MoviesService){
    
  }

  ngOnInit(): void {
    this.param$=this.activatedRoute.params.subscribe(
      async (params)=> {
        this.query = params['query']
        const reviews = await this.moviesSvc.getMovies(this.query)
        this.movies = reviews
        this.movies.forEach(
          (review)=>{
            if (review.image=='null'){
              review.image='../../assets/placeholder.jpg'
            }
          }
        )
        if (this.movies.length == 0){
          this.noResult = true
        }
      }
    )
      
  }

  addComment(title: string){
    this.router.navigate(['/commentMovie', title+"|"+this.query])
  }



}
