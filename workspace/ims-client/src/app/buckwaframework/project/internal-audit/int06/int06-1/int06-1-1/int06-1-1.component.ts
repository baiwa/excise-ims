import { Component, OnInit } from '@angular/core';

import { NgForm } from '@angular/forms';
import { Int0611Service } from './int06-1-1.service';
import { BreadCrumb } from '../../../../../common/models';

declare var $: any;

@Component({
  selector: 'app-int06-1-1',
  templateUrl: './int06-1-1.component.html',
  styleUrls: ['./int06-1-1.component.css'],
  providers: [Int0611Service]
})
export class Int0611Component implements OnInit {

  breadcrumb: BreadCrumb[];
  loading: boolean;
  tableLoading : boolean;
  constructor(
    private int0611Service : Int0611Service
  ) {
    this.breadcrumb = [
      { label: "ตรวจสอบภายใน", route: "#" },
      { label: "ตรวจสอบเบิกจ่าย", route: "#" },
      { label: "ตรวจสอบค่าใช้จ่าย", route: "#" },
    ];
    this.loading = false;
    this.tableLoading = false;
  }


  ngOnInit() {
  } 
  ngAfterViewInit() {
    this.dataTable();
  }

  async onSubmit(f: any) {   
    this.tableLoading = await true; 
    const form = $("#upload-form")[0];
    let formBody = new FormData(form);
    this.int0611Service.onSubmit(formBody).then(()=>{
      this.tableLoading = false; 
    });
  }

  async onChangeUpload(file:any){    
    this.loading = await true;
    await this.int0611Service.onChangeUpload(file);
    setTimeout(() => {
      this.loading = false;
    }, 1000);
  }

  dataTable(){
    this.int0611Service.dataTable();
  }
}
