
import { Router } from '@angular/router';
import { ExciseService } from './../../../../../common/services/excise.service';
import { TextDateTH, formatter } from './../../../../../common/helper/datepicker';
import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../../../../../common/services';


declare var jQuery: any;
declare var $: any;


@Component({
  selector: 'app-int09-2-1-2',
  templateUrl: './int09-2-1-2.component.html'
})
export class Int09212Component implements OnInit {

  sectorList : any;
  dataDropdown : any;
  constructor(
    private exciseService:ExciseService,
    private router: Router,
    private ajax: AjaxService
   ) {}

  calenda = () =>{
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

  dropdownSector = () => {    
    const URL = "ia/int09212/dropdownHeader";
    this.ajax.get(URL, res => {
      this.sectorList = res.json();
    });
  }
  sectorOnchange = (e) => {
    let date = { lovIdMaster: e.target.value }
    const URL = "ia/int09212/listDropdown";
    this.ajax.post(URL, JSON.stringify(this.dataDropdown), res => {
      console.log("Response : ", res.json());
      this.dataDropdown = res.json();
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
  ngOnInit() {
    $('.ui.dropdown').dropdown();
    this.dropdownSector();
  }

  ngAfterViewInit(){
    
    this.calenda();
  }

}
