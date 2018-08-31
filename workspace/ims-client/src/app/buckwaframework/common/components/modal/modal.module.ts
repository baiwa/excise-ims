import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ModalComponent } from './modal.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule
    ],
    declarations: [
        ModalComponent
    ],
    exports: [
        // Component
        ModalComponent,
        // Modules
        CommonModule,
        FormsModule
    ]
})
export class ModalModule { }