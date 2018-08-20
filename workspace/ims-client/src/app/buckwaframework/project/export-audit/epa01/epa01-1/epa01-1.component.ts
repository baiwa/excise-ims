import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-epa01-1',
  templateUrl: './epa01-1.component.html',
  styleUrls: ['./epa01-1.component.css']
})
export class Epa011Component implements OnInit {

  ValidChars  = "0123456789.";
  IsNumber= true;
  Char : string;

  constructor() { }

  ngOnInit() {

    var num:number = 5; 
    var i:number; 
    var factorial = 1;    
    
    for(i = num;i>=1;i--) {
       factorial *= i;
    }
    console.log(factorial)

    
  }


}
