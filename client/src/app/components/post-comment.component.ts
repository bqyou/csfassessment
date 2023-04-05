import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MoviesService } from '../services/movies.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { MovieComment } from '../models/Comment';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit{

  form!: FormGroup
  param$!: Subscription
  titlequery!: any
  query!: string
  title!: string

  constructor(private activatedRoute: ActivatedRoute, private router: Router,
    private movieService: MoviesService, private fb: FormBuilder){}

  ngOnInit(): void {
    this.param$ = this.activatedRoute.params.subscribe(
      (params)=> {
        this.titlequery = params['title'].split('|')
        this.title = this.titlequery[0]
        this.query = this.titlequery[1]
        console.log(this.title)
        console.log(this.query)
      }
    )
    this.form = this.createForm()
      
  }

  createForm():FormGroup{
    return this.fb.group({
      title: this.title,
      name: this.fb.control('', [Validators.required, Validators.minLength(3)]),
      rating: this.fb.control<number>(1, [Validators.required, Validators.min(1), Validators.max(5)]),
      comment: this.fb.control('', [Validators.required])
    })
  }

  isFormInvalid(){
    return this.form.invalid
  }

  postComment(){
    const c = {} as MovieComment
    c.title = this.title
    c.rating = this.form?.value['rating']
    c.comment = this.form?.value['comment']
    c.name = this.form?.value['name']

    this.movieService.postComments(c).then(
      (result: string) => {
        console.log(result)
      }
    )
    this.router.navigate(['/searchMovie', this.query])
  }

  goBack(){
    this.form.reset()
    this.router.navigate(['/searchMovie', this.query])
  }

}
