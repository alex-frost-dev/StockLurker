import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TransactionsService } from '../../services/transactions.service';
import { AssetsService } from '../../../asset/services/asset.services';
import { Market } from '../../../markets/dtos/markets.dto';
import { MarketsService } from '../../../markets/services/markets.services';

@Component({
  selector: 'app-add-transaction',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-transaction.component.html'
})
export class AddTransactionComponent {
  private fb = inject(FormBuilder);
  private router = inject(Router);
  private marketsService = inject(MarketsService);
  private transactionsService = inject(TransactionsService);
  private assetsService = inject(AssetsService);

  isLoading = signal<boolean>(false);
  errorMessage = signal<string | null>(null);
  successMessage = signal<string | null>(null);
  markets = signal<Market[]>([]);

  transactionForm: FormGroup = this.fb.group({
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
      transactionType: ['BUY', [Validators.required]]
    })
  });

  ngOnInit() {
    this.loadMarkets();
    this.transactionForm.get('position.asset.isin')?.valueChanges.subscribe(value => {
      const trimmedValue = value ? value.trim() : '';
      console.log('ISIN input changed:', trimmedValue);

      if (trimmedValue.length === 12) {
        this.onIsinComplete(trimmedValue);
      }
    });
  }
  
  onIsinComplete(isin: string) {
    this.assetsService.getAssetOnineInfo(isin).subscribe({
      next: (asset) => {
        this.transactionForm.patchValue({
          position: {
            asset: {
              name: asset.name
            }
          }
        });
      },
      error: (err) => {
        this.errorMessage.set('Failed to fetch asset information for ISIN: ' + isin);
        console.error(err);
      }
    });
  }

  loadMarkets() {
    this.marketsService.getAllMarkets().subscribe({
      next: (response) => {
        // Assuming your backend returns ApiResponse<List<MarketDTO>>
        this.markets.set(response.data); 
      },
      error: (err) => {
        console.error('Failed to load markets', err);
      }
    });
  }

  onSubmit(): void {
    if (this.transactionForm.invalid) {
      this.transactionForm.markAllAsTouched();
      this.errorMessage.set('Please fill out all required fields correctly.');
      return;
    }

    this.isLoading.set(true);
    this.errorMessage.set(null);
    this.successMessage.set(null);

    const formValue = this.transactionForm.value;
    
    // Format timestamp back to ISO Instant string
    formValue.position.timestamp = new Date(formValue.position.timestamp).toISOString();
    this.transactionsService.addNewTransaction(formValue).subscribe({
      next: (data) => {
        this.isLoading.set(false);
        this.successMessage.set('Transaction registered successfully!');
        setTimeout(() => this.router.navigate(['/transactions']), 1500);
      },
      error: (err) => {
        this.isLoading.set(false);
        this.errorMessage.set('ERROR: ' + (err?.error?.data || 'Unknown error'));
        console.error(err);
      }
    });
    console.log('Submitting AddAssetTransactionRequest:', formValue);
  }
}