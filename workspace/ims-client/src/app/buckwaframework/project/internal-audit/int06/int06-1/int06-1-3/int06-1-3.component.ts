import { Component, OnInit } from '@angular/core';
import { Int0613Service } from 'projects/internal-audit/int06/int06-1/int06-1-3/int06-1-3.service';
import { From } from 'projects/internal-audit/int06/int06-1/int06-1-3/form.model';

@Component({
  selector: 'app-int06-1-3',
  templateUrl: './int06-1-3.component.html',
  styleUrls: ['./int06-1-3.component.css'],
  providers: [Int0613Service]
})
export class Int0613Component implements OnInit {

  form: From = new From();
  sectorList : any;
  araeList : any;
  constructor(
    private int0613Servicen: Int0613Service
  ) { }

  ngOnInit() {
  }

  ngAfterViewInit() {
    this.dataTable();
  }

  getSector=()=>{
    this.int0613Servicen.sector();
  }

  getArea=()=>{

  }
  search = () => {
    this.int0613Servicen.search();
  }

  dataTable = () => {
    this.int0613Servicen.dataTable();
  }
}

