import { Component, OnInit } from '@angular/core';
import { TextDateTH, formatter } from 'helpers/datepicker';
declare var $: any;
@Component({
  selector: 'app-int06-9',
  templateUrl: './int06-9.component.html',
  styleUrls: ['./int06-9.component.css']
})
export class Int069Component implements OnInit {

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
    $('#int069').show();
  }
  hidedata(){
    $('#int069').hide();
  }
  popupEditData() {
    $('#int0699').modal('show');
  }

  closePopupEdit() {
    $('#int0699').modal('hide');
  }

}
