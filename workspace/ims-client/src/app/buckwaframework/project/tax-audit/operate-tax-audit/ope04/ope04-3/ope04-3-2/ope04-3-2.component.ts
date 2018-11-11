import { Component, OnInit } from '@angular/core';
import { BreadCrumb } from 'models/breadcrumb';
import { Ope0432Service } from './ope04-3-2.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Utils } from 'helpers/utils';

@Component({
  selector: 'app-ope04-3-2',
  templateUrl: './ope04-3-2.component.html',
  styleUrls: ['./ope04-3-2.component.css'],
  providers:[Ope0432Service]
})
export class Ope0432Component implements OnInit {
 
  breadcrumb: BreadCrumb[] = [
    { label: 'ตรวจสอบภาษี', route: '#' },
    { label: 'การตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบภาษี', route: '#' },
    { label: 'ผลการตรวจสอบกระดาษทำการรับวัตถุดิบ', route: '#' },
  ]

  // ==> params
  formControl: FormGroup;
  id: string = "";
  constructor(
    private myService: Ope0432Service,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.id = this.route.snapshot.queryParams["id"];
    if (Utils.isNull(this.id)) this.router.navigate(['/ope04/2-1']);
   }

  ngOnInit() {        
    this.datatable(this.id);
  }

  claer = () => {
  }

  datatable = (id: any) => {
    this.myService.datatable(id);
  }
}
