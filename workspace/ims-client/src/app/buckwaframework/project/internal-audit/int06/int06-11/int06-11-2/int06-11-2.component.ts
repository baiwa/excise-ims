import { Component, OnInit } from '@angular/core';
import { AuthService } from 'services/auth.service';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';

declare var $: any;

@Component({
  selector: 'app-int06-11-2',
  templateUrl: './int06-11-2.component.html',
  styleUrls: ['./int06-11-2.component.css']
})
export class Int06112Component implements OnInit {

  medicalWelfareForm = this.fb.group({
    fullName: [''],
    gender: [''],
    birthDate: [''],
    siblingsOrder: [''],
    position: [''],
    affiliation: [''],
    phoneNumber: [''],
    status: [''],
    disease: [''],
    hospitalName: [''],
    hospitalOwner: [''],
    treatedDateFrom: [''],
    treatedDateTo: [''],
    totalMoney: [''],
    receiptQt: [''],
    claimStatus: [''],
    claimMoney: [''],
    ownerClaim: [''],
    otherClaim: [''],
    mateName: [''],
    mateCitizenId: [''],
    fatherName: [''],
    fatherCitizenId: [''],
    motherName: [''],
    motherCitizenId: [''],
    childName: [''],
    childCitizenId: [''],
    fileName: ['']
  });

  payLoad = '';

  constructor(
    private authService: AuthService,
    private fb: FormBuilder
  ) { }

  ngOnInit() {
    this.authService.reRenderVersionProgram('INT-06112');
  }

  onSubmit() {
    console.warn(this.medicalWelfareForm.value);
  }
}
