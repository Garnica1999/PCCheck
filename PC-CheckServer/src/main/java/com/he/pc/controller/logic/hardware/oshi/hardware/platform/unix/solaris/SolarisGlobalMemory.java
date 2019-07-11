/**
 * OSHI (https://github.com/oshi/oshi)
 *
 * Copyright (c) 2010 - 2019 The OSHI Project Team:
 * https://github.com/oshi/oshi/graphs/contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.he.pc.controller.logic.hardware.oshi.hardware.platform.unix.solaris;

import com.sun.jna.platform.unix.solaris.LibKstat.Kstat; // NOSONAR

import com.he.pc.controller.logic.hardware.oshi.hardware.VirtualMemory;
import com.he.pc.controller.logic.hardware.oshi.hardware.common.AbstractGlobalMemory;
import com.he.pc.controller.logic.hardware.oshi.util.ExecutingCommand;
import com.he.pc.controller.logic.hardware.oshi.util.ParseUtil;
import com.he.pc.controller.logic.hardware.oshi.util.platform.unix.solaris.KstatUtil;

/**
 * Memory obtained by kstat
 */
public class SolarisGlobalMemory extends AbstractGlobalMemory {

    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    @Override
    public long getAvailable() {
        if (this.memAvailable < 0) {
            updateSystemPages();
        }
        return this.memAvailable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getTotal() {
        if (this.memTotal < 0) {
            updateSystemPages();
        }
        return this.memTotal;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getPageSize() {
        if (this.pageSize < 0) {
            this.pageSize = ParseUtil.parseLongOrDefault(ExecutingCommand.getFirstAnswer("pagesize"), 4096L);
        }
        return this.pageSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VirtualMemory getVirtualMemory() {
        if (this.virtualMemory == null) {
            this.virtualMemory = new SolarisVirtualMemory();
        }
        return this.virtualMemory;
    }

    private void updateSystemPages() {
        // Get first result
        Kstat ksp = KstatUtil.kstatLookup(null, -1, "system_pages");
        // Set values
        if (ksp != null && KstatUtil.kstatRead(ksp)) {
            this.memAvailable = KstatUtil.kstatDataLookupLong(ksp, "availrmem") * getPageSize();
            this.memTotal = KstatUtil.kstatDataLookupLong(ksp, "physmem") * getPageSize();
        }
    }
}
