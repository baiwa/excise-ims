import { Component, OnInit } from '@angular/core';
import { TextDateTH, formatter } from 'helpers/datepicker';
declare var $: any;
@Component({
  selector: 'app-int06-10',
  templateUrl: './int06-10.component.html',
  styleUrls: ['./int06-10.component.css']
})
export class Int0610Component implements OnInit {

  constructor() { }

  ngOnInit() {
    this.hidedata();

    $("#calendar1").calendar({    
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
     formatter: formatter()
      
  
    });
    $("#calendar2").calendar({    
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter: formatter()
       
  
    });

  }
  showdata(){
    $('#int0610').show();
  }
  hidedata(){
    $('#int0610').hide();
  }
 

}