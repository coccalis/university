/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "ex_calc.h"

bool_t
xdr_InputData (XDR *xdrs, InputData *objp)
{
	register int32_t *buf;

	 if (!xdr_int (xdrs, &objp->n))
		 return FALSE;
	 if (!xdr_float (xdrs, &objp->r))
		 return FALSE;
	 if (!xdr_pointer (xdrs, (char **)&objp->X, sizeof (float), (xdrproc_t) xdr_float))
		 return FALSE;
	 if (!xdr_pointer (xdrs, (char **)&objp->Y, sizeof (float), (xdrproc_t) xdr_float))
		 return FALSE;
	return TRUE;
}
