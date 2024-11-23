package com.example.logointerpreterbeta.functions

import android.util.Log
import com.example.logointerpreterbeta.visitors.DebuggerVisitor

fun toggleBreakpoint(lineNumber: Int) {
    Log.i("Breakpoint", "Toggled breakpoint at line $lineNumber")
    if (DebuggerVisitor.breakpoints.contains(lineNumber)) {
        DebuggerVisitor.breakpoints.remove(lineNumber)
    } else {
        DebuggerVisitor.breakpoints.add(lineNumber)
    }
}