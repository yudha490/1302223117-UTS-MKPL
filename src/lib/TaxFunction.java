package lib;

public class TaxFunction {

	private static final int BulanSetahun = 12;
    private static final double TaxRate = 0.05;
    private static final int singleTax = 54000000;
    private static final int BonusNikah = 4500000;
    private static final int DeductionPerAnak = 1500000;
    private static final int MaxAnakDeduction = 3;
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		int tax = 0;
		
		if (numberOfMonthWorking > BulanSetahun) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > MaxAnakDeduction) {
			numberOfChildren = MaxAnakDeduction;
		}
		
		if (isMarried) {
			tax = (int) Math.round(TaxRate * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (singleTax + BonusNikah + (numberOfChildren * DeductionPerAnak))));
		}else {
			tax = (int) Math.round(TaxRate * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - singleTax));
		}
		
		if (tax < 0) {
			return 0;
		}else {
			return tax;
		}
			 
	}
	
}
