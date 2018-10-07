import { Component, OnInit } from '@angular/core';
import { Int0613Service } from 'projects/internal-audit/int06/int06-1/int06-1-3/int06-1-3.service';
import { From } from 'projects/internal-audit/int06/int06-1/int06-1-3/form.model';
import { BreadCrumb } from 'models/breadcrumb';
import { Int061Service } from 'projects/internal-audit/int06/int06-1/int06-1.service';
declare var $: any;
@Component({
  selector: 'app-int06-1-3',
  templateUrl: './int06-1-3.component.html',
  styleUrls: ['./int06-1-3.component.css'],
  providers: [Int0613Service,Int061Service]
})
export class Int0613Component implements OnInit {

  form: From = new From();
  sectorList: any;
  araeList: any;
  yearList: any;
  //value init
  breadcrumb: BreadCrumb[] = [
    { label: "ตรวจสอบภายใน", route: "#" },
    { label: "ตรวจสอบเบิกจ่าย", route: "#" },
    { label: "ตรวจสอบค่าใช้จ่าย", route: "#" },
  ];
  constructor(
    private int0613Servicen: Int0613Service
  ) { }

  ngOnInit() {
    this.callDropdown();
  }

  ngAfterViewInit() {
    this.dataTable();
    this.getSector();
    this.year();
  }

  getSector = () => {
    this.int0613Servicen.sector(this.callBackSector);
  }

  changeSector = (e) => {
    if(e.target.value != null && e.target.value != ""){
      $("#arae").dropdown('restore defaults');
      let idMaster = e.target.value;
      this.int0613Servicen.area(idMaster, this.callBackArea);
    }   
  }

  year = () => {
    this.int0613Servicen.year(this.callBackYear);
  }
  search = () => {
    this.int0613Servicen.search();
  }

  clear = async() => {   
    await this.int0613Servicen.clear(); 
    await $(".ui.dropdown").dropdown('restore defaults');
    
  }

  dataTable = () => {
    this.int0613Servicen.dataTable();
  }

  callDropdown = () => {
    $(".ui.dropdown").dropdown();
  }

  callBackSector = (result) => {
    this.sectorList = result;
  }
  callBackArea = (result) => {
    this.araeList = result;
  }
  callBackYear = (result) => {
    this.yearList = result;
  }

}

