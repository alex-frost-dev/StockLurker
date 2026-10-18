// add-exchange.component.ts
import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ExchangesService } from '../../services/exchanges.service';
// Import your actual service when ready:
// import { ExchangesService } from '../../services/exchanges.service';

@Component({
  selector: 'app-add-exchange',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-exchange.component.html'
})
export class AddExchangeComponent {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private exchangesService = inject(ExchangesService);

  isLoading = signal<boolean>(false);
  errorMessage = signal<string | null>(null);
  successMessage = signal<string | null>(null);

  // Form matching AddAssetExchangeRequest structure
  exchangeForm: FormGroup = this.fb.group({
    position: this.fb.group({
      asset: this.fb.group({
        name: ['', [Validators.required]],
        description: [''],
        isin: ['', [Validators.required, Validators.minLength(12), Validators.maxLength(12)]]
      }),
      shares: [0, [Validators.required, Validators.min(0.0001)]],
      price: [0, [Validators.required, Validators.min(0)]],
      timestamp: [new Date().toISOString().slice(0, 16), [Validators.required]], // datetime-local format
      market: ['', [Validators.required]],
      commission: [0, [Validators.required, Validators.min(0)]],
      tax: [0, [Validators.required, Validators.min(0)]],
      exchangeType: ['BUY', [Validators.required]]
    })
  });

  onSubmit(): void {
    if (this.exchangeForm.invalid) {
      this.exchangeForm.markAllAsTouched();
      this.errorMessage.set('Please fill out all required fields correctly.');
      return;
    }

    this.isLoading.set(true);
    this.errorMessage.set(null);
    this.successMessage.set(null);

    const formValue = this.exchangeForm.value;
    
    // Format timestamp back to ISO Instant string
    formValue.position.timestamp = new Date(formValue.position.timestamp).toISOString();
    this.exchangesService.addNewExchange(formValue).subscribe({
      next: (data) => {
        this.isLoading.set(false);
        this.successMessage.set('Exchange registered successfully!');
        setTimeout(() => this.router.navigate(['/exchanges']), 1500);
      },
      error: (err) => {
        this.isLoading.set(false);
        this.errorMessage.set('Failed to save exchange.');
        console.error(err);
      }
    });
    console.log('Submitting AddAssetExchangeRequest:', formValue);
    

    // TODO: Replace with actual service call
    // this.exchangesService.addExchange(formValue).subscribe({
    //   next: () => {
    //     this.isLoading.set(false);
    //     this.successMessage.set('Exchange registered successfully!');
    //     setTimeout(() => this.router.navigate(['/exchanges']), 1500);
    //   },
    //   error: (err) => {
    //     this.isLoading.set(false);
    //     this.errorMessage.set('Failed to save exchange.');
    //     console.error(err);
    //   }
    // });

    // Simulating success for preview:
    // setTimeout(() => {
    //   this.isLoading.set(false);
    //   this.successMessage.set('Exchange registered successfully!');
    // }, 1000);
  }
}