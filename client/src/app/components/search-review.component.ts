import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MoviesService } from '../services/movies.service';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit{

  form!: FormGroup

  constructor(private router: Router, private fb: FormBuilder){
    
  }

  ngOnInit(): void {

    this.form = this.createForm()
      
  }

  noWhitespaceValidator(control: FormControl) {
    const isLessThanTwo = (control.value || '').trim().length < 2;
    const isValid = !isLessThanTwo;
    return isValid ? null : { 'whitespace': true };
}

  createForm():FormGroup{
    return this.fb.group({
      query: this.fb.control('', [Validators.required, Validators.minLength(2), this.noWhitespaceValidator])
    })
  }

  isFormInvalid(){
    return this.form.invalid
  }

  searchMovie(){
    const query = this.form.value['query']


    console.log("search >>>" + query )
    this.router.navigate(['/searchMovie', query])
  }

  

}
