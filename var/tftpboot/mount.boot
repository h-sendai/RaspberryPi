#!/bin/sh

do_mount_overlayfs()
{
    local serial=$1
    mkdir -p $serial

    lower=raspberry-pi-os/boot
    upper=upper/${serial}
    work=work/${serial}
    mkdir -p $upper
    mkdir -p $work

    # does not need nfs for boot partion (tftp only)
    mount -t overlay overlay -o lowerdir=${lower},upperdir=${upper},workdir=${work} ${serial}
}

do_mount_overlayfs aabbccdd
do_mount_overlayfs eeffgghh
do_mount_overlayfs iijjkkll
