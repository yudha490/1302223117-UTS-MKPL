package lib;

public class TaxFunction {

	private static final int BulanSetahun = 12;
	private static final double TaxRate = 0.05;
	private static final int singleTax = 54000000;
	private static final int BonusNikah = 4500000;
	private static final int DeductionPerAnak = 1500000;
	private static final int MaxAnakDeduction = 3;

	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
	 * dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan
	 * bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan)
	 * dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena
	 * pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah
	 * sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya
	 * ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome,
			int numberOfMonthsWorked, int deductible,
			boolean isMarried, int numberOfChildren) {

		validateInput(numberOfMonthsWorked);

		int taxThreshold = calculateTaxThreshold(isMarried, numberOfChildren);
		int taxableIncome = calculateTaxableIncome(monthlySalary, otherMonthlyIncome,
				numberOfMonthsWorked, deductible,
				taxThreshold);

		int tax = (int) Math.round(TaxRate * taxableIncome);

		return Math.max(tax, 0);
	}

	private static void validateInput(int numberOfMonthsWorked) {
		if (numberOfMonthsWorked > BulanSetahun) {
			throw new IllegalArgumentException("Months worked cannot exceed " + BulanSetahun);
		}
	}

	private static int calculateTaxThreshold(boolean isMarried, int numberOfChildren) {
		int threshold = singleTax;

		if (isMarried) {
			threshold += BonusNikah;
		}

		int eligibleChildren = Math.min(numberOfChildren, MaxAnakDeduction);
		threshold += eligibleChildren * DeductionPerAnak;

		return threshold;
	}

	private static int calculateTaxableIncome(int monthlySalary, int otherMonthlyIncome, 
                                        int numberOfMonthsWorked, int deductible, 
                                        int taxThreshold) {
    return ((monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked) - deductible - taxThreshold;
}
}
