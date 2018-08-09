import { Router } from '@angular/router';
import { ExciseService } from './../../../../../common/services/excise.service';
import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';


declare var jQuery: any;
declare var $: any;

@Component({
  selector: 'app-int09-2-1-2',
  templateUrl: './int09-2-1-2.component.html'
})
export class Int09212Component implements OnInit {

  constructor(
    private exciseService:ExciseService,
    private router: Router,
   ) {}

  calenda = function(){
    $("#startDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter : formatter()
    });
    $("#endDate").calendar({
      maxDate: new Date(),
      type: "date",
      text: TextDateTH,
      formatter : formatter()
    });
  }

  saveAndNext = event => {
    const { startDateInput,endDateInput, sector,area, branch ,department} = event.target;
    const data = {
      startDateInput: startDateInput.value,
      endDateInput : endDateInput.value,
      sector : sector.value,
      area : area.value,
      branch : branch.value,
      department : department.value
    };
    this.exciseService.setData(data);
    this.exciseService.getData();
    setTimeout(() => { this.router.navigate(["/int09/2-1-3"]) }, 200);
  }
  ngOnInit() {}

  ngAfterViewInit(){
    this.calenda();
  }

}
